package com.vti.shoppee.service;

import com.vti.shoppee.modal.dto.OrderCreateRequestDto;
import com.vti.shoppee.modal.dto.OrderSearchRequest;
import com.vti.shoppee.modal.dto.OrderUpdateRequest;
import com.vti.shoppee.modal.entity.Order;
import com.vti.shoppee.modal.entity.StatusOrder;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();
    List<Order> findByStatus(StatusOrder status, int accountId);
    Order create(OrderCreateRequestDto dto);
    Order buy(int orderId);
    Order cancel(int orderId);
    Order getById(int id);
    Order update(OrderUpdateRequest dto);
    Page<Order> search(OrderSearchRequest request);
}
