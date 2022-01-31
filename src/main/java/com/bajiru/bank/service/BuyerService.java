package com.bajiru.bank.service;


import com.bajiru.bank.domain.OrderMaster;

/**
 * 买家
 * Created by 廖师兄
 * 2017-06-22 00:11
 */
public interface BuyerService {

    //查询一个订单
    OrderMaster findOrderOne(String customerId, String orderId);

    //取消订单
    OrderMaster cancelOrder(String customerId, String orderId);
}
