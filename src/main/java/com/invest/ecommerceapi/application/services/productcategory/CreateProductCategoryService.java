package com.invest.ecommerceapi.application.services.productcategory;

import com.invest.ecommerceapi.domain.schemas.CreateProductCategoryRequestSchema;
import com.invest.ecommerceapi.domain.schemas.ProductCategoryResponseSchema;
import com.invest.ecommerceapi.infra.entities.ProductCategory;
import com.invest.ecommerceapi.infra.repositories.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategoryResponseSchema create(CreateProductCategoryRequestSchema requestModel) {
        requestModel.setName(requestModel.getName().toUpperCase());
        var categoryAlreadyExists = this.productCategoryRepository.findByName(requestModel.getName());
        if (categoryAlreadyExists != null) return null;
        var category = this.productCategoryRepository.save(new ProductCategory(requestModel.getName()));
        return new ProductCategoryResponseSchema(category.getId(), category.getName());
    }
}
