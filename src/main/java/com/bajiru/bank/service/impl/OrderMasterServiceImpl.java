package com.bajiru.bank.service.impl;

import com.bajiru.bank.domain.OrderDetail;
import com.bajiru.bank.domain.OrderMaster;
import com.bajiru.bank.domain.ProductInfo;
import com.bajiru.bank.dto.CartDTO;
import com.bajiru.bank.enums.OrderStatusEnum;
import com.bajiru.bank.enums.PayStatusEnum;
import com.bajiru.bank.enums.ResultEnum;
import com.bajiru.bank.exception.SellException;
import com.bajiru.bank.service.OrderMasterService;
import com.bajiru.bank.service.repository.OrderDetailRepository;
import com.bajiru.bank.service.repository.OrderMasterRepository;
import com.bajiru.bank.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description create OrderMaster Service Implements
 * @AuthorName StevenWu
 * @CreateDateTime 2022-01-27-10:51 PM
 */
@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    @Transactional
    public OrderMaster create(OrderMaster orderMaster) {

        String orderId= KeyUtil.genUniqueKey();
        BigDecimal orderAmount=new BigDecimal(BigInteger.ZERO);

        //1.find product quantity and price
        for(OrderDetail orderDetail: orderMaster.getOrderDetails()){
            ProductInfo productInfo=productInfoService.findOne(orderDetail.getProductInfo().getProductId());
            if(productInfo ==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2. Total price
            orderAmount=productInfo.getProductPrice()
                    .multiply(new BigDecimal((orderDetail.getProductQuantity()))).add(orderAmount);
            //save to orderDetail
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderMaster(orderMaster);
            orderDetailRepository.save(orderDetail);
        }
        //3.save to OrderMaster
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //4. reduce quantity
        List<CartDTO> cartDTOList=orderMaster.getOrderDetails().stream().map(e ->
                new CartDTO(e.getProductInfo().getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);
        return orderMaster;
    }

    @Override
    public OrderMaster findOne(String orderId) {
        OrderMaster orderMaster=orderMasterRepository.findById(orderId).orElse(null);
        if(orderMaster ==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderMaster;
    }

    @Override
    public Page<OrderMaster> findList(String customerId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage=orderMasterRepository.findOrderMastersByCustomer_Id(customerId, pageable);
        List<OrderMaster> orderMasterList=orderMasterPage.getContent();
        return new PageImpl<OrderMaster>(orderMasterList, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderMaster cancel(OrderMaster orderMaster) {
        //OrderMaster Status
        if(!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【Cancel OrderMaster】status incorrect, OrderMasterId={}, orderMasterStatus={} ", orderMaster.getOrderId(),orderMaster.getOrderStatus() );
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //change OrderMaster status
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster updateResult=orderMasterRepository.save(orderMaster);
        if(updateResult==null){
            log.error("【Cancel OrderMaster】update fail, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //update stock
        if(CollectionUtils.isEmpty(orderMaster.getOrderDetails())){
            log.error("【Cancel OrderMaster】orderMaster={}, orderMaster");
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList=orderMaster.getOrderDetails().stream()
                .map(e -> new CartDTO(e.getProductInfo().getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);

        //refund if paid//TODO

        return orderMaster;
    }

    @Override
    @Transactional
    public OrderMaster finish(OrderMaster orderMaster) {
        //check orderMaster status
        if(!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【OrderMaster finish】orderMaster status incorrect, orderId={}, orderStatus={}", orderMaster.getOrderId(), orderMaster.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //update orderMaster status
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster updateResult=orderMasterRepository.save(orderMaster);
        if(updateResult ==null){
            log.error("【OrderMaster finish】update fail, orderMaster={}", orderMaster);
        }
        return orderMaster;
    }

    @Override
    public OrderMaster paid(OrderMaster orderMaster) {
        if(!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【OrderMaster paid】,orderMaster status incorrect, orderId={}, orderStatus={}", orderMaster.getOrderId(), orderMaster.getOrderStatus());
        }
        if(!orderMaster.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【OrderMaster paid】orderMaster pay status incorrect , orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //update orderMaster pay status
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster updateResult=orderMasterRepository.save(orderMaster);
        if(updateResult==null){
            log.error("【OrderMaster paid】, update failed, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderMaster;
    }

    @Override
    public Page<OrderMaster> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage=orderMasterRepository.findAll(pageable);
        List<OrderMaster> orderMasterList=orderMasterPage.getContent();
        return new PageImpl<>(orderMasterList, pageable, orderMasterPage.getTotalElements());
    }
}
