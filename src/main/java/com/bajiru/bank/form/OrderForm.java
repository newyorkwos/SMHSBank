package com.bajiru.bank.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by 廖师兄
 * 2017-06-18 23:31
 */
@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "Name, Do not Blank")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "Phone number, Do not Blank")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "Address, Do not Blank")
    private String address;

    /**
     * 买家微信openid
     */
    @NotEmpty(message = "CustomerId, do not blank")
    private String customerId;

    /**
     * 购物车
     */
    @NotEmpty(message = "Shopping Cart, Do not Blank")
    private String items;
}
