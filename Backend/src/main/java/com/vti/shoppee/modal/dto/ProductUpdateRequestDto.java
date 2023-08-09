package com.vti.shoppee.modal.dto;

import com.vti.shoppee.modal.entity.ProductStatus;
import com.vti.shoppee.modal.entity.ProductType;
import com.vti.shoppee.modal.entity.ShippingUnit;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class ProductUpdateRequestDto {
    @NotNull(message = "Không được để null")
    @Positive(message = "Id phải lớn hơn 0")
    private int id;

    @NotBlank(message = "Không được để null")
    @Length(max = 50, message = "Name không được quá 50 ký tự")
    private String name;

    private String  image;

    @NotNull(message = "Không được để trống giá")
    @PositiveOrZero(message = "Giá phải lớn hơn hoặc bằng 0")
    private int price;

    private ProductStatus status;

    private ShippingUnit shippingUnit;

    private ProductType type;
}
