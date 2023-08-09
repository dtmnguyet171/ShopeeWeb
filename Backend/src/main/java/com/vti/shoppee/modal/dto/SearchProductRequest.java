package com.vti.shoppee.modal.dto;

import com.vti.shoppee.modal.entity.ProductStatus;
import com.vti.shoppee.modal.entity.ProductType;
import com.vti.shoppee.modal.entity.ShippingUnit;
import lombok.Data;

import java.util.Set;

@Data
public class SearchProductRequest extends BaseRequest{
    private String name;

    private int minPrice;

    private int maxPrice;

    private Set<ProductType> type;

    private Set<ProductStatus> status;

    private Set<ShippingUnit> shippingUnit;
}
