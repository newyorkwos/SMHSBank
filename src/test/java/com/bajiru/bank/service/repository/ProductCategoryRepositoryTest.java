package com.bajiru.bank.service.repository;

import com.bajiru.bank.domain.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Test
    public void setNewCategory(){
        for(int i=1;i<=5;i++){
            ProductCategory productCategory=new ProductCategory();
            productCategory.setCategoryName("hotLevel"+i);
            productCategory.setCategoryType(i);
            productCategoryRepository.save(productCategory);
        }
    }

   @Test
    public void findOneTest(){
        ProductCategory productCategory=productCategoryRepository.findById(1).orElse(null);
       // Assert.notNull(productCategory,"has something");
    }
}
