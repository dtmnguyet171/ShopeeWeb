package com.vti.shoppee.modal.dto;

import com.vti.shoppee.modal.entity.ProductStatus;
import com.vti.shoppee.modal.entity.ProductType;
import com.vti.shoppee.modal.entity.ShippingUnit;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@Data
public class ProductCreateRequestDto {
    @NotBlank(message = "{product.create.name.notBlank}")
    @Length(max = 50, message = "{product.create.name.length}")
    private String name;

    private String  image;

    @NotNull(message = "{product.create.price.notNull}")
    @PositiveOrZero(message = "Giá phải lớn hơn hoặc bằng 0")
    private int price;

    private ProductStatus status;

    private ShippingUnit shippingUnit;

    private ProductType type;
}
