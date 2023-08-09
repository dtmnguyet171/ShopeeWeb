package com.vti.shoppee.modal.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "`Account`")
public class Account extends BaseEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username",length = 50,unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "`password`")
    private String password;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "address",length = 255)
    private String address;

    @Column(name = "full_name",length = 50)
    private String fullName;

    @Column(name = "phone_number",length = 12)
    private String phoneNumber;

    @Column(name = "email",length = 50)
    private String email;

    @Column(name = "facebook",length = 50)
    private String facebook;

    @Column(name = "information",length = 255)
    private String information;

}
