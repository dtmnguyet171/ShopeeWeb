package com.vti.shoppee.controller;

import com.vti.shoppee.modal.dto.AccountCreateRequestDto;
import com.vti.shoppee.modal.dto.AccountSearchRequest;
import com.vti.shoppee.modal.dto.AccountUpdateRequestDto;
import com.vti.shoppee.modal.entity.Account;
import com.vti.shoppee.service.iml.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@CrossOrigin("*")
@Validated
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/get-all")
    public List<Account> getAll() {
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable int id){
        return accountService.getById(id);
    }

    @PostMapping("/create")
    public Account create(@RequestBody @Valid AccountCreateRequestDto dto){
        return accountService.create(dto);
    }

    @PutMapping("/update")
    public Account update(@RequestBody @Valid AccountUpdateRequestDto dto){
        return accountService.update(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String deleteById(@PathVariable int id){
        accountService.delete(id);
        return "Đã xoá thành công";
    }

    @PostMapping("/search")
    public Page<Account> search(@RequestBody AccountSearchRequest request) {
        return accountService.search(request);
    }
}
