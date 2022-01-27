package com.bajiru.bank.service.repository;

import com.bajiru.bank.domain.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @AuthorName StevenWu
 * @CreateDateTime 2022-01-22-11:41 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void findByCustomerId(){
        PageRequest request=PageRequest.of(0,1);
        Page<OrderMaster> productInfoList=repository.findOrderMastersByCustomer_Id("3",request);
        System.out.println(productInfoList.getTotalElements());
    }
}
