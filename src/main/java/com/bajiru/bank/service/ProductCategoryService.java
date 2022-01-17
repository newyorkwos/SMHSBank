package com.bajiru.bank.service;


import com.bajiru.bank.domain.ProductCategory;

import java.util.List;

/**
 * @Description create ProductCategory class
 * @AuthorName StevenWu
 * @CreateDateTime 2022-01-16-7:40 AM
 */

public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    ProductCategory save(ProductCategory productCategory);
}
