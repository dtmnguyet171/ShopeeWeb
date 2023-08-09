package com.vti.shoppee.controller;

import com.vti.shoppee.config.exception.AppException;
import com.vti.shoppee.config.exception.ErrorResponseEnum;
import com.vti.shoppee.modal.dto.LoginDto;
import com.vti.shoppee.modal.dto.LoginRequest;
import com.vti.shoppee.modal.entity.Account;
import com.vti.shoppee.repository.AccountRepository;
import com.vti.shoppee.utils.JWTTokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    /**
     * Hàm login: Cần set authenticated() cho api này
     * @param principal: Đối tượng được sinh ra khi đã xác thực được người dùng
     * @return
     */
    @GetMapping("/login-basic-v1")
    public LoginDto loginV1(Principal principal){
        String username = principal.getName();
        // Tìm ra được đối tượng Account từ username
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if (accountOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.NOT_FOUND_ACCOUNT);
        }
        Account account = accountOptional.get();
        LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(account, loginDto);
        return loginDto;
    }

    /**
     * Hàm login: Cách này cần permitAll() với api này để xử lý dữ liệu
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/login-basic-v2")
    public LoginDto loginV2(@RequestParam String username, @RequestParam String password) {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if (accountOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.NOT_EXISTED_USERNAME);
        }
        Account account = accountOptional.get();
        boolean checkPassword = passwordEncoder.matches(password, account.getPassword());
        if (!checkPassword) {
            throw new AppException(ErrorResponseEnum.WRONG_PASSWORD);
        }
        LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(account, loginDto);
        return loginDto;
    }

    /**
     * Hàm login JWT: Cách này cần permitAll() với api này để xử lý dữ liệu
     * @param request: Đối tượng gồm username và password
     * @return
     */
    @PostMapping("/login-jwt")
    public LoginDto loginJWT(@RequestBody LoginRequest request) {
        Optional<Account> accountOptional = accountRepository.findByUsername(request.getUsername());
        if (accountOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.NOT_EXISTED_USERNAME);
        }
        Account account = accountOptional.get();
        boolean checkPassword = passwordEncoder.matches(request.getPassword(), account.getPassword());
        if (!checkPassword) {
            throw new AppException(ErrorResponseEnum.WRONG_PASSWORD);
        }

        // Login
        LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(account, loginDto);
        loginDto.setUserAgent(httpServletRequest.getHeader("User-Agent"));
        String token = jwtTokenUtils.createAccessToken(loginDto);
        loginDto.setToken(token);
        return loginDto;
    }

}
