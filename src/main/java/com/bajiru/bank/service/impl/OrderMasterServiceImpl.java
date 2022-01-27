package com.bajiru.bank.service.impl;

import com.bajiru.bank.domain.OrderDetail;
import com.bajiru.bank.domain.OrderMaster;
import com.bajiru.bank.domain.ProductInfo;
import com.bajiru.bank.enums.ResultEnum;
import com.bajiru.bank.exception.SellException;
import com.bajiru.bank.service.OrderMasterService;
import com.bajiru.bank.service.repository.OrderMasterRepository;
import com.bajiru.bank.service.repository.ProductInfoRepository;
import com.bajiru.bank.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Description create OrderMaster Service Implements
 * @AuthorName StevenWu
 * @CreateDateTime 2022-01-27-10:51 PM
 */
@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public OrderMaster create(OrderMaster orderMaster) {

        String orderId= KeyUtil.genUniqueKey();
        BigDecimal orderAmount=new BigDecimal(BigInteger.ZERO);

        //1.find product quantity and price
        for(OrderDetail orderDetail: orderMaster.getOrderDetails()){
            ProductInfo productInfo=productInfoRepository.findById(orderDetail.getProductInfo().getProductId()).orElse(null);
            if(productInfo ==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
        }
        //2. Total price

        //3. save to dataTable
        //4. reduce quantity
        return null;
    }

    @Override
    public OrderMaster findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderMaster> findList(String customerId) {
        return null;
    }

    @Override
    public OrderMaster cancel(OrderMaster orderMaster) {
        return null;
    }

    @Override
    public OrderMaster finish(OrderMaster orderMaster) {
        return null;
    }

    @Override
    public OrderMaster paid(OrderMaster orderMaster) {
        return null;
    }
}
