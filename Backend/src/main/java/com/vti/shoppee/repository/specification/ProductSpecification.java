package com.vti.shoppee.repository.specification;

import com.vti.shoppee.modal.dto.SearchProductRequest;
import com.vti.shoppee.modal.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> buildCondition(SearchProductRequest request) {
        return Specification.where(buildConditionName(request)).and(buildConditionType(request))
                .and(buildConditionShippingUnit(request)).and(buildConditionStatus(request))
                .and(buildConditionPrice(request));
    }

    public static Specification<Product> buildConditionName(SearchProductRequest request) {
        if (request.getName() != null && request.getName() != "") {
            return (root, query, cri) -> {
                // root: chọn cột, field để tìm kiếm (giá trị là thuộc tính trong java)
                // query
                // cri: khai báo loại so sánh dữ liệu (lớn hơn, nhỏ hơn, equal, like...)
                return cri.like(root.get("name"), "%" + request.getName() + "%");
            };
        } else {
                return null;
            }
    }

    public static Specification<Product> buildConditionType(SearchProductRequest request) {
        if (request.getType() != null && request.getType().size() > 0) {
            return (root, query, cri) -> {
                return root.get("type").in(request.getType());
            };
        } else {
            return null;
        }
    }

    private static Specification<Product> buildConditionShippingUnit(SearchProductRequest request) {
        if (request.getShippingUnit() != null && request.getShippingUnit().size() > 0) {
            return (root, query, cri) -> {
                return root.get("shippingUnit").in(request.getShippingUnit());
            };
        } else {
            return null;
        }
    }

    private static Specification<Product> buildConditionStatus(SearchProductRequest request) {
        if (request.getStatus() != null && request.getStatus().size() > 0) {
            return (root, query, cri) -> {
                return root.get("status").in(request.getStatus());
            };
        } else {
            return null;
        }
    }

    public static Specification<Product> buildConditionPrice(SearchProductRequest request) {
        if (request.getMinPrice()>=0 && request.getMaxPrice()!=0){
            return (root, query, cri )-> {
                return cri.between(root.get("price"), request.getMinPrice(),request.getMaxPrice());
            };
        } else {
            return null;
        }
    }
}
