package com.vti.shoppee.modal.dto;

import com.vti.shoppee.modal.entity.Account;
import com.vti.shoppee.modal.entity.Product;
import com.vti.shoppee.modal.entity.StatusOrder;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class OrderSearchRequest extends BaseRequest{

    private int accountId;

    private int productId;

    private StatusOrder status;
}
