package com.vti.shoppee.repository.specification;

import com.vti.shoppee.modal.dto.AccountSearchRequest;
import com.vti.shoppee.modal.entity.Account;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {
    public static Specification<Account> buildCondition(AccountSearchRequest request){
        return Specification.where(buildConditionUsername(request)).and(buildConditionRole(request))
                .and(buildConditionFullName(request)).and(buildConditionPhoneNumber(request))
                .and(buildConditionEmail(request));
    }

    public static Specification<Account> buildConditionUsername(AccountSearchRequest request) {
        if (request.getUsername() != null && request.getUsername() != "") {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("username"),"%" + request.getUsername() + "%");
            };
        } else {
            return null;
        }
    }

    public static Specification<Account> buildConditionRole(AccountSearchRequest request) {
        if (request.getRole() != null) {
            return (root, query, criteriaBuilder) -> {
                return root.get("role").in(request.getRole());
            };
        } else {
                return null;
        }
    }

    public static Specification<Account> buildConditionFullName(AccountSearchRequest request) {
        if (request.getFullName() != null && request.getFullName() != "") {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("fullName"), "%" + request.getFullName() + "%");
            };
        } else {
            return null;
        }
    }

    public static Specification<Account> buildConditionPhoneNumber(AccountSearchRequest request) {
        if (request.getPhoneNumber() != null && request.getPhoneNumber() != "") {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("phoneNumber"),"%" + request.getPhoneNumber() + "%");
            };
        } else {
            return null;
        }
    }

    public static Specification<Account> buildConditionEmail(AccountSearchRequest request) {
        if (request.getEmail() != null && request.getEmail() != "") {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("email"),"%" + request.getEmail() + "%");
            };
        } else {
            return null;
        }
    }
}
