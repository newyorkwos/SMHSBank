package com.bajiru.bank.service.impl;

import com.bajiru.bank.domain.Customer;
import com.bajiru.bank.service.CustomerService;
import com.bajiru.bank.service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
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
    public Customer findCustomerById(String customerId) {
        return null;
    }
}
