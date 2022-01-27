package com.bajiru.bank.service.impl;

import com.bajiru.bank.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @AuthorName StevenWu
 * @CreateDateTime 2022-01-22-5:12 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {
    @Autowired
    private CustomerServiceImpl repository;

    @Test
    public void save() {
        Customer customer=new Customer();

            customer.setId("01");
            customer.setUsername("NewYork");
            customer.setPassword("pass1234");
            repository.save(customer);

    }
}
