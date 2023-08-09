package com.vti.shoppee.repository.specification;

import com.vti.shoppee.modal.dto.OrderSearchRequest;
import com.vti.shoppee.modal.entity.Account;
import com.vti.shoppee.modal.entity.Order;
import com.vti.shoppee.modal.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class OrderSpecification {
    public static Specification<Order> buildCondition(OrderSearchRequest request) {
        return Specification.where(buildConditionAccount(request)).and(buildConditionProduct(request))
                .and(buildConditionStatus(request));
    }

    public static Specification<Order> buildConditionAccount(OrderSearchRequest request) {
        if (request.getAccountId() > 0) {
            return (root, query, criteriaBuilder) -> {
                Join<Order, Account> join = root.join("account");
                return criteriaBuilder.equal(join.get("id"), request.getAccountId());
            };
        } else {
            return null;
        }
    }

    public static Specification<Order> buildConditionProduct(OrderSearchRequest request) {
        if (request.getProductId() > 0) {
            return (root, query, criteriaBuilder) -> {
                Join<Order, Product> join = root.join("product");
                return criteriaBuilder.equal(join.get("id"), request.getProductId());
            };
        } else {
            return null;
        }
    }

    public static Specification<Order> buildConditionStatus(OrderSearchRequest request) {
        if (request.getStatus() != null) {
            return (root, query, criteriaBuilder) -> {
                return root.get("status").in(request.getStatus());
            };
        } else {
            return null;
        }
    }
}
