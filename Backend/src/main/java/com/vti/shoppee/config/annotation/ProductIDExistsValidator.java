package com.vti.shoppee.config.annotation;

import com.vti.shoppee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductIDExistsValidator implements ConstraintValidator<ProductIDExists, Integer> {
    @Autowired
    ProductRepository repository;

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        // Logic kiểm tra dữ liệu
        return repository.existsById(integer);
    }
}


