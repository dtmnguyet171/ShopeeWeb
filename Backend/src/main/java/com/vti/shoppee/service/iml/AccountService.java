package com.vti.shoppee.service.iml;

import com.vti.shoppee.config.exception.AppException;
import com.vti.shoppee.config.exception.ErrorResponseEnum;
import com.vti.shoppee.modal.dto.AccountCreateRequestDto;
import com.vti.shoppee.modal.dto.AccountSearchRequest;
import com.vti.shoppee.modal.dto.AccountUpdateRequestDto;
import com.vti.shoppee.modal.entity.Account;
import com.vti.shoppee.modal.entity.Role;
import com.vti.shoppee.repository.AccountRepository;
import com.vti.shoppee.repository.specification.AccountSpecification;
import com.vti.shoppee.service.IAccountService;
import com.vti.shoppee.service.IMailSenderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService, UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private IMailSenderService mailSenderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account create(AccountCreateRequestDto dto) {
        Optional<Account> optionalAccount = accountRepository.findByUsername(dto.getUsername());
        if (optionalAccount.isPresent()) {
            throw new AppException(ErrorResponseEnum.EXISTED_USERNAME);
        }
            Account account = new Account();
            BeanUtils.copyProperties(dto, account); // Bên trái set giá trị, bên hải lấy giá trị
            String passEncoder = passwordEncoder.encode(dto.getPassword());
            account.setPassword(passEncoder);
            account.setRole(Role.CUSTOMER);
            String text = "Bạn vừa tạo tài khoản có usename là: " + dto.getUsername()
                    + ". Để kích hoạt tài khoản click "
                    + "<a href=\""+ "\" target=\"_blank\">Click me </a>";
            mailSenderService.sendMessageWithHtml(dto.getEmail(), "Thông báo tới tài khoản!", text);
            return accountRepository.save(account);
    }

    @Override
    public Account update(AccountUpdateRequestDto dto) {
        Optional<Account> optionalAccount = accountRepository.findById(dto.getId());
        if (optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            BeanUtils.copyProperties(dto, account);
            return accountRepository.save(account);
        }
        else {
            throw new AppException(ErrorResponseEnum.NOT_FOUND_ACCOUNT);
        }
    }

    @Override
    public Account getById(int id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()){
            throw new AppException(ErrorResponseEnum.NOT_FOUND_ACCOUNT);
        } else {
            return accountOptional.get();
        }
    }

    @Override
    public void delete(int id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()){
            throw new AppException(ErrorResponseEnum.NOT_FOUND_ACCOUNT);
        } else {
            accountRepository.deleteById(id);
        }
    }

    @Override
    public Page<Account> search(AccountSearchRequest request) {
        PageRequest pageRequest = null;
        if ("DESC".equals(request.getSortType())) {
            pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getSortField()).descending());
        } else {
            pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getSortField()).ascending());
        }
        Specification<Account> condition = AccountSpecification.buildCondition(request);
        return accountRepository.findAll(condition, pageRequest);
    }

    /**
     * Dùng để Spring security kiểm tra username có tồn tại trong hệ thống hay không
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if (accountOptional.isEmpty()){
            throw new UsernameNotFoundException("Username không tồn tại");
        }
        Account account = accountOptional.get();
        // Nếu có tồn tại -> tạo ra đối tượng Userdetails từ Account
        /**
         * username
         * account.getPassword(): password đã được mã hóa
         * authorities: List quyền của user
         */
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(account.getRole());
        return new User(username, account.getPassword(), authorities);
    }
}
