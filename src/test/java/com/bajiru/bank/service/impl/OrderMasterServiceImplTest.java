package com.bajiru.bank.service.impl;

import com.bajiru.bank.domain.Customer;
import com.bajiru.bank.domain.OrderDetail;
import com.bajiru.bank.domain.OrderMaster;
import com.bajiru.bank.enums.OrderStatusEnum;
import com.bajiru.bank.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @AuthorName StevenWu
 * @CreateDateTime 2022-01-28-4:13 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterServiceImpl orderMasterService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    private final String CUSTOMER_ID="01";
    private final String ORDER_ID="1643403818285805297";

    @Test
    public void create() {

        OrderMaster orderMaster=new OrderMaster();
        Customer customer=customerService.findById("01");
        orderMaster.setCustomer(customer);
        List<OrderDetail> orderDetailList=new ArrayList<>();
        OrderDetail o1=new OrderDetail();
        o1.setProductInfo(productInfoService.findOne("1"));
        o1.setProductQuantity(1);

        OrderDetail o2=new OrderDetail();
        o2.setProductInfo(productInfoService.findOne("2"));
        o2.setProductQuantity(2);

        orderDetailList.add(o1);
        orderDetailList.add(o2);
        orderMaster.setOrderDetails(orderDetailList);

        OrderMaster result=orderMasterService.create(orderMaster);
        log.info("【create OrderMaster】result={}", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne(){
        OrderMaster result=orderMasterService.findOne(ORDER_ID);
        log.info("find OrderMaster result={}",result);
        Assert.assertEquals(ORDER_ID,result.getOrderId());
    }

    @Test
    public void findList(){
        PageRequest request=PageRequest.of(0,2);
        Page<OrderMaster> orderMasterPage=orderMasterService.findList(CUSTOMER_ID, request);
        Assert.assertNotEquals(0, orderMasterPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderMaster orderMaster = orderMasterService.findOne(ORDER_ID);
        OrderMaster result = orderMasterService.cancel(orderMaster);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderMaster orderMaster = orderMasterService.findOne(ORDER_ID);
        OrderMaster result = orderMasterService.finish(orderMaster);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderMaster orderMaster = orderMasterService.findOne(ORDER_ID);
        OrderMaster result = orderMasterService.paid(orderMaster);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }

    @Test
    public void list() {
        PageRequest request = PageRequest.of(0,2);
        Page<OrderMaster> orderDTOPage = orderMasterService.findList(request);
//        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
        Assert.assertTrue("find all OrderMaster", orderDTOPage.getTotalElements() > 0);
    }


}
