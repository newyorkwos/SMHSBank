package com.bajiru.bank.service.impl;

import com.bajiru.bank.domain.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @AuthorName StevenWu
 * @CreateDateTime 2022-01-16-9:22 AM
 */
@SpringBootTest
class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl productCategoryServiceImpl;

    @Test
    void findOne() {
        ProductCategory productCategory=productCategoryServiceImpl.findOne(1);
        Assert.notNull(productCategory,"Success");
    }

    @Test
    void findAll() {
        List<ProductCategory> productCategoryList=productCategoryServiceImpl.findAll();
        Assert.notEmpty(productCategoryList,"Success");
    }

    @Test
    void findByCategoryTypeIn() {
        List<Integer> intList= Arrays.asList(2,3,4);
        List<ProductCategory> result=productCategoryServiceImpl.findByCategoryTypeIn(intList);
        Assert.notEmpty(result,"success");
    }

    @Test
    void save() {
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryId(11);
        productCategory.setCategoryName("hot11");
        productCategory.setCategoryType(11);
        ProductCategory result=productCategoryServiceImpl.save(productCategory);
        Assert.notNull(result,"success");
    }
}
