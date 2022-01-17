package com.bajiru.bank.service.repository;

import com.bajiru.bank.domain.ProductCategory;
import com.bajiru.bank.domain.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @Description
 * @AuthorName StevenWu
 * @CreateDateTime 2022-01-17-10:27 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void saveTest(){
        ProductInfo productInfo=new ProductInfo();
        ProductCategory productCategory=new ProductCategory();
        for(int i=1; i<=10; i++ ){
            productInfo.setProductName("pancake"+i);
            productInfo.setProductId(String.valueOf(i));
            productInfo.setProductPrice(new BigDecimal(10+i));
            productInfo.setProductStock(100);
            productInfo.setProductDescription("Test good!");
            productInfo.setProductStatus(0);
            productInfo.setProductIcon("https://picsum.photos/id/493/200/300");
            productInfo.setProductCategory(productCategoryRepository.findByCategoryType(1));
            ProductInfo result=repository.save(productInfo);


        }
    }

    @Test
    public void findByProductStatus() {

    }
}
