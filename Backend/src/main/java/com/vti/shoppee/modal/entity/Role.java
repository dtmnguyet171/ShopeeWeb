package com.vti.shoppee.modal.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * inplements GrantedAuthority: đang coi 1 đối tượng enum Role là một quyền trong Security
 */
public enum Role implements GrantedAuthority {
    SELLER, CUSTOMER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
