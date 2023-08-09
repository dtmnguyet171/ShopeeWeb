package com.vti.shoppee.modal.dto;

import com.vti.shoppee.config.annotation.NotAdmin;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class AccountCreateRequestDto {
    @NotBlank(message = "Username không được để trống")
    @Length(max = 50, message = "Username không được quá 50 ký tự")
    @NotAdmin
    private String username;

    @NotBlank(message = "Password không được để trống")
    @Length(max = 50, message = "Password không được quá 50 ký tự")
    private String password;

    private Date dateOfBirth;

    private String address;

    @NotBlank(message = "Full name không được để trống")
    @Length(max = 50, message = "Full name không được quá 50 ký tự")
    private String fullName;

    @NotBlank(message = "SĐT không được để trống")
    @Length(max = 12, message = "SĐT không được quá 12 ký tự")
    private String phoneNumber;

    @NotBlank(message = "Email không được để trống")
    @Length(max = 50, message = "Email không được quá 50 ký tự")
    private String email;

    private String information;
}
