package com.bajiru.bank.service.impl;

import com.bajiru.bank.domain.Customer;
import com.bajiru.bank.service.CustomerService;
import com.bajiru.bank.service.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }

    @Override
    @Transactional
    public Customer findById(String customerId) {
        Customer customer=repository.getById(customerId);
        log.info("customerid="+customer.getId());
        return customer;
    }
}
