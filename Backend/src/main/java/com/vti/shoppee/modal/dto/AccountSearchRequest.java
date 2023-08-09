package com.vti.shoppee.modal.dto;

import com.vti.shoppee.modal.entity.Role;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
public class AccountSearchRequest extends BaseRequest{
    private String username;

    private Set<Role> role;

    private String fullName;

    private String phoneNumber;

    private String email;
}
