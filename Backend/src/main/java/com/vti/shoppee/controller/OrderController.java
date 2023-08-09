package com.vti.shoppee.controller;

import com.vti.shoppee.modal.dto.AccountCreateRequestDto;
import com.vti.shoppee.modal.dto.OrderCreateRequestDto;
import com.vti.shoppee.modal.dto.OrderSearchRequest;
import com.vti.shoppee.modal.dto.OrderUpdateRequest;
import com.vti.shoppee.modal.entity.Account;
import com.vti.shoppee.modal.entity.Order;
import com.vti.shoppee.modal.entity.StatusOrder;
import com.vti.shoppee.service.iml.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin("*")
@Validated
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/get-by-status")
    public List<Order> findByStatus(@RequestParam(required = false) StatusOrder status, @RequestParam int accountId){
        return orderService.findByStatus(status, accountId);
    }
    @PostMapping("/create")
    public Order create(@RequestBody @Valid OrderCreateRequestDto dto) {
        return orderService.create(dto);
    }

    @PostMapping("/buy/{id}")
    public Order buy(@PathVariable int id) {
        return orderService.buy(id);
    }

    @PostMapping("/cancel/{id}")
    public Order cancel(@PathVariable int id) {
        return orderService.cancel(id);
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable int id) {
        return orderService.getById(id);
    }

    @PutMapping("/update")
    public Order update(@RequestBody @Valid OrderUpdateRequest dto) {
        return orderService.update(dto);
    }

    @PostMapping("/search")
    public Page<Order> search(@RequestBody OrderSearchRequest request) {
        return orderService.search(request);
    }
}
