package com.bajiru.bank.service;

import com.bajiru.bank.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Description create OrderMaster Service
 * @AuthorName StevenWu
 * @CreateDateTime 2022-01-27-4:01 PM
 */
public interface OrderMasterService  {

    /*create OrderMaster*/
    OrderMaster create (OrderMaster orderMaster);

    /*find the OrderMaster*/
    OrderMaster findOne(String orderId);

    /*find the OrderMaster List by customerId*/
    Page<OrderMaster> findList(String customerI, Pageable pageable);

    /*cancel OrderMaster*/
    OrderMaster cancel(OrderMaster orderMaster);

    /*finished OrderMaster*/
    OrderMaster finish(OrderMaster orderMaster);

    /*OrderMaster paid*/
    OrderMaster paid(OrderMaster orderMaster);

    /*find all OrderMasters in page mode*/
    Page<OrderMaster> findList(Pageable pageable);
}
