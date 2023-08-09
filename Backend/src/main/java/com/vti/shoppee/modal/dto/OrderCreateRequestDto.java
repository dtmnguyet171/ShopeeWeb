package com.vti.shoppee.modal.dto;

import com.vti.shoppee.modal.entity.Account;
import com.vti.shoppee.modal.entity.Product;
import com.vti.shoppee.modal.entity.StatusOrder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
public class OrderCreateRequestDto {
    @NotNull(message = "Không được để null")
    @Positive(message = "Id phải lớn hơn 0")
    private int productId;

    @NotNull(message = "Không được để null")
    @Positive(message = "Id phải lớn hơn 0")
    private int accountId;

    @NotNull(message = "Không được để null")
    @Positive(message = "số lượng phải lớn hơn 0")
    private int quantity;
}
