package com.bajiru.bank.converter;

import com.bajiru.bank.domain.Customer;
import com.bajiru.bank.domain.OrderDetail;
import com.bajiru.bank.domain.OrderMaster;
import com.bajiru.bank.enums.ResultEnum;
import com.bajiru.bank.exception.SellException;
import com.bajiru.bank.form.OrderForm;
import com.bajiru.bank.service.impl.CustomerServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 廖师兄
 * 2017-06-18 23:41
 */
@Slf4j
public class OrderForm2OrderMasterConverter {

    @Autowired
    private CustomerServiceImpl customerService;

    public static OrderMaster convert(OrderForm orderForm) {
        Gson gson = new Gson();
        Customer customer=new Customer();
        customer.setId(orderForm.getCustomerId());

        OrderMaster orderMaster = new OrderMaster();

        orderMaster.setBuyerName(orderForm.getName());
        orderMaster.setBuyerPhone(orderForm.getPhone());
        orderMaster.setBuyerAddress(orderForm.getAddress());
        orderMaster.setCustomer(customer);
       // orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderMaster.setOrderDetails(orderDetailList);

        return orderMaster;
    }
}
