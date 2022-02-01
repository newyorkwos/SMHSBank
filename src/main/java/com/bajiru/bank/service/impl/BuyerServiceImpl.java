package com.bajiru.bank.service.impl;


import com.bajiru.bank.domain.OrderMaster;
import com.bajiru.bank.enums.ResultEnum;
import com.bajiru.bank.exception.SellException;
import com.bajiru.bank.service.BuyerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 廖师兄
 * 2017-06-22 00:13
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderMasterServiceImpl orderMasterService;

    @Override
    public OrderMaster findOrderOne(String customerId, String orderId) {
        return checkOrderOwner(customerId, orderId);
    }

    @Override
    public OrderMaster cancelOrder(String customerId, String orderId) {
        OrderMaster orderMaster = checkOrderOwner(customerId, orderId);
        if (orderMaster == null) {
            log.error("【取消订单】查不到改订单, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderMasterService.cancel(orderMaster);
    }

    private OrderMaster checkOrderOwner(String customerId, String orderId) {
        OrderMaster orderMaster = orderMasterService.findOne(orderId);
        if (orderMaster == null) {
            return null;
        }
        //判断是否是自己的订单
        if (!orderMaster.getCustomer().getId().equalsIgnoreCase(customerId)){
            log.error("【查询订单】订单的openid不一致. openid={}, orderDTO={}", customerId, orderMaster);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderMaster;
    }
}
