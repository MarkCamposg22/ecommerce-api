package com.invest.ecommerceapi.application.services.productcategory;

import com.invest.ecommerceapi.domain.schemas.ProductCategoriesResponseSchema;
import com.invest.ecommerceapi.domain.schemas.ProductCategoryResponseSchema;
import com.invest.ecommerceapi.infra.entities.ProductCategory;
import com.invest.ecommerceapi.infra.repositories.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllProductCategoriesService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategoriesResponseSchema getAll() {
        List<ProductCategory> productCategories = this.productCategoryRepository.findAll();
        List<ProductCategoryResponseSchema> responseSchemas = productCategories.stream()
                .map(this::mapToResponseSchema)
                .collect(Collectors.toList());
        return new ProductCategoriesResponseSchema(responseSchemas);
    }

    private ProductCategoryResponseSchema mapToResponseSchema(ProductCategory productCategory) {
        return new ProductCategoryResponseSchema(productCategory.getId(), productCategory.getName());
    }
}
