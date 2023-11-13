package com.invest.ecommerceapi.infra.repositories;

import com.invest.ecommerceapi.infra.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {
    ProductCategory findByName(String name);
}
