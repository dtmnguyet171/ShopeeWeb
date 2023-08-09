package com.vti.shoppee.service;

import com.vti.shoppee.modal.dto.AccountCreateRequestDto;
import com.vti.shoppee.modal.dto.AccountSearchRequest;
import com.vti.shoppee.modal.dto.AccountUpdateRequestDto;
import com.vti.shoppee.modal.entity.Account;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAccountService {
    List<Account> getAll();

    Account create(AccountCreateRequestDto dto);

    Account update(AccountUpdateRequestDto dto);

    Account getById(int id);

    void delete(int id);

    Page<Account> search(AccountSearchRequest request);

}
