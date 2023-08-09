package com.vti.shoppee.modal.dto;

import com.vti.shoppee.modal.entity.StatusOrder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
public class OrderUpdateRequest {
    @NotNull(message = "Không được để null")
    @Positive(message = "Id phải lớn hơn 0")
    private int id;

    @NotNull(message = "Không được để null")
    @Positive(message = "Id phải lớn hơn 0")
    private int productId;

    @NotNull(message = "Không được để null")
    @Positive(message = "Id phải lớn hơn 0")
    private int accountId;

    @NotNull(message = "Không được để null")
    @Positive(message = "số lượng phải lớn hơn 0")
    private int quantity;

    @Pattern(regexp = "PENDING|DONE|CANCEL", message = "Trạng thái không hợp lệ")
    private String status;

}
