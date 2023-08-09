package com.vti.shoppee.config.exception;

import org.springframework.http.HttpStatus;

public enum ErrorResponseEnum {
    EXISTED_USERNAME(400, "Username đã tồn tại"),
    NOT_FOUND_ACCOUNT(404, "Không tìm thấy account"),
    NOT_EXISTED_USERNAME(401, "Sai username"),
    WRONG_PASSWORD(401, "Sai mật khẩu"),

    NOT_FOUND_PRODUCT(404, "Không tìm thấy sản phẩm"),

    NOT_FOUND_ORDER(404, "Không tìm thấy đơn hàng"),
    NOT_FOUND_DONE_ORDER(404, "Không tìm thấy đơn hàng hoàn thành"),
    NOT_FOUND_CANCEL_ORDER(404, "Không tìm thấy đơn hàng hủy");


    public final int status;
    public final String message;
    ErrorResponseEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
