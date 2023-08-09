package com.vti.shoppee.service.iml;

import com.vti.shoppee.config.exception.AppException;
import com.vti.shoppee.config.exception.ErrorResponseEnum;
import com.vti.shoppee.modal.dto.OrderCreateRequestDto;
import com.vti.shoppee.modal.dto.OrderSearchRequest;
import com.vti.shoppee.modal.dto.OrderUpdateRequest;
import com.vti.shoppee.modal.entity.Account;
import com.vti.shoppee.modal.entity.Order;
import com.vti.shoppee.modal.entity.Product;
import com.vti.shoppee.modal.entity.StatusOrder;
import com.vti.shoppee.repository.AccountRepository;
import com.vti.shoppee.repository.OrderRepository;
import com.vti.shoppee.repository.ProductRepository;
import com.vti.shoppee.repository.specification.OrderSpecification;
import com.vti.shoppee.service.IOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByStatus(StatusOrder status, int accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isEmpty()){
            throw new AppException(ErrorResponseEnum.NOT_FOUND_ACCOUNT);
        }
        if (status != null){
            return orderRepository.findAllByStatusAndAccount_Id(status, accountId);
        } else {
            return orderRepository.findAllByAccount_Id(accountId);
        }
    }

    @Override
    public Order create(OrderCreateRequestDto dto) {
        Optional<Account> accountOptional = accountRepository.findById(dto.getAccountId());
        Optional<Product> productOptional = productRepository.findById(dto.getProductId());
        if (accountOptional.isEmpty()){
            throw new AppException(ErrorResponseEnum.NOT_FOUND_ACCOUNT);
        }
        if (productOptional.isEmpty()){
            throw new AppException(ErrorResponseEnum.NOT_FOUND_PRODUCT);
        }
        Account account = accountOptional.get();
        Product product = productOptional.get();

        Order order = new Order();
        order.setAccount(account);
        order.setProduct(product);
        order.setQuantity(dto.getQuantity());
        order.setStatus(StatusOrder.PENDING);
        return orderRepository.save(order);
    }

    @Override
    public Order buy(int orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()){
            Order order = orderOptional.get();
            order.setStatus(StatusOrder.DONE);
            return orderRepository.save(order);
        }
        throw new AppException(ErrorResponseEnum.NOT_FOUND_DONE_ORDER);
    }

    @Override
    public Order cancel(int orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()){
            Order order = orderOptional.get();
            order.setStatus(StatusOrder.CANCEL);
            return orderRepository.save(order);
        }
        throw new AppException(ErrorResponseEnum.NOT_FOUND_CANCEL_ORDER);
    }

    @Override
    public Order getById(int id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            return orderOptional.get();
        } else {
           throw new AppException(ErrorResponseEnum.NOT_FOUND_ORDER);
        }
    }

    @Override
    public Order update(OrderUpdateRequest dto) {
        Order order = getById(dto.getId());
        if (order != null){
            BeanUtils.copyProperties(dto, order);
            return orderRepository.save(order);
        }
        throw new AppException(ErrorResponseEnum.NOT_FOUND_ORDER);
    }

    @Override
    public Page<Order> search(OrderSearchRequest request) {
        PageRequest pageRequest = null;
        if ("DESC".equals(request.getSortType())) {
            pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getSortField()).descending());
        } else {
            pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getSortField()).descending());
        }
        Specification<Order> condition = OrderSpecification.buildCondition(request);
        return orderRepository.findAll(condition, pageRequest);
    }
}
