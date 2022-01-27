package com.bajiru.bank.service.repository;

import com.bajiru.bank.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description create customer repository
 * @AuthorName StevenWu
 * @CreateDateTime 2022-01-22-5:02 PM
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
