package com.bajiru.bank.service.repository;


import com.bajiru.bank.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 廖师兄
 * 2017-06-11 17:24
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    Page<OrderMaster> findOrderMastersByCustomer_Id(String customerId, Pageable pageable);
   // Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
