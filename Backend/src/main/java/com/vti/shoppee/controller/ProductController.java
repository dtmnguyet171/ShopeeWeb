package com.vti.shoppee.controller;

import com.vti.shoppee.config.annotation.ProductIDExists;
import com.vti.shoppee.modal.dto.BaseRequest;
import com.vti.shoppee.modal.dto.ProductCreateRequestDto;
import com.vti.shoppee.modal.dto.ProductUpdateRequestDto;
import com.vti.shoppee.modal.dto.SearchProductRequest;
import com.vti.shoppee.modal.entity.Product;
import com.vti.shoppee.service.iml.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin("*")
@Validated
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/get-all")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable @ProductIDExists int id){
        return productService.getById(id);
    }

    @PostMapping("/create")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public Product create(@Valid @RequestBody ProductCreateRequestDto dto) {
        return productService.create(dto);
    }

    @PutMapping("/update")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public Product update(@RequestBody @Valid ProductUpdateRequestDto dto) {
        return productService.update(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String deleteById(@PathVariable @ProductIDExists int id) {
        productService.deleteById(id);
        return "Đã xoá thành công";
    }

    @PostMapping("/search")
    public Page<Product> search(@RequestBody SearchProductRequest request) {
        return productService.search(request);
    }
}
