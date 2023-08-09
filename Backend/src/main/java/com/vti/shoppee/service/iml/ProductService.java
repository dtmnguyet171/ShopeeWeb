package com.vti.shoppee.service.iml;

import com.vti.shoppee.config.exception.AppException;
import com.vti.shoppee.config.exception.ErrorResponseEnum;
import com.vti.shoppee.modal.dto.*;
import com.vti.shoppee.modal.entity.Product;
import com.vti.shoppee.repository.ProductRepository;
import com.vti.shoppee.repository.specification.ProductSpecification;
import com.vti.shoppee.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            return optionalProduct.get();
        } else {
            throw new AppException(ErrorResponseEnum.NOT_FOUND_PRODUCT);
        }
    }

    @Override
    public Product create(ProductCreateRequestDto dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto,product);
        return productRepository.save(product);
    }

    @Override
    public Product update(ProductUpdateRequestDto dto) {
        Product product = getById(dto.getId());
        if (product!=null){
            BeanUtils.copyProperties(dto,product);
            return productRepository.save(product);
        } else {
            throw new AppException(ErrorResponseEnum.NOT_FOUND_PRODUCT);
        }
    }

    @Override
    public void deleteById(int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new AppException(ErrorResponseEnum.NOT_FOUND_PRODUCT);
        }
    }

    @Override
    public Page<Product> search(SearchProductRequest request) {
        PageRequest pageRequest = null;
        if ("DESC".equals(request.getSortType())) {
            pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getSortField()).descending());
        } else {
            pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getSortField()).descending());
        }
        Specification<Product> condition = ProductSpecification.buildCondition(request);
        return productRepository.findAll(condition, pageRequest);
    }
}
