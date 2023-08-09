package com.vti.shoppee.config.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * ConstraintValidator<A, T>;
 * Trong đó:    A: Là annotation vừa tạo ở bước 1
 *              B: Là kiểu dữ liệu nó muốn validate
 */
public class NotAdminValidator implements ConstraintValidator<NotAdmin, String> {
    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return (!name.contains("ADMIN"));
    }
}
