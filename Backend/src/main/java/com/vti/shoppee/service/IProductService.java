package com.vti.shoppee.service;

import com.vti.shoppee.modal.dto.BaseRequest;
import com.vti.shoppee.modal.dto.ProductCreateRequestDto;
import com.vti.shoppee.modal.dto.ProductUpdateRequestDto;
import com.vti.shoppee.modal.dto.SearchProductRequest;
import com.vti.shoppee.modal.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    List<Product> getAll();

    Product getById(int id);

    Product create(ProductCreateRequestDto dto);

    Product update(ProductUpdateRequestDto dto);

    void deleteById(int id);

    Page<Product> search(SearchProductRequest request);

}
