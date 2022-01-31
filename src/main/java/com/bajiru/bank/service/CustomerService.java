package com.bajiru.bank.service;

import com.bajiru.bank.domain.Customer;

/**
 * @Description create Customer Service
 * @AuthorName StevenWu
 * @CreateDateTime 2022-01-22-2:29 PM
 */
public interface CustomerService {
    Customer save(Customer customer);
    Customer update(Customer customer);
    Customer findById(String customerId);
}
