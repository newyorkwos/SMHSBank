package com.bajiru.bank.controller;

import com.bajiru.bank.VO.ResultVO;
import com.bajiru.bank.converter.OrderForm2OrderMasterConverter;
import com.bajiru.bank.domain.OrderMaster;
import com.bajiru.bank.enums.ResultEnum;
import com.bajiru.bank.exception.SellException;
import com.bajiru.bank.form.OrderForm;
import com.bajiru.bank.service.impl.BuyerServiceImpl;
import com.bajiru.bank.service.impl.OrderMasterServiceImpl;
import com.bajiru.bank.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description create customer's orderMaster controller
 * @AuthorName StevenWu
 * @CreateDateTime 2022-01-30-10:13 AM
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class CustomerOrderController {
    @Autowired
    private OrderMasterServiceImpl orderMasterService;

    @Autowired
    private BuyerServiceImpl buyerService;


    //create customer orderMaster
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderMaster orderMaster= OrderForm2OrderMasterConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderMaster.getOrderDetails())){
            log.error("【create orderMaster】shopping cart is empty");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderMaster result=orderMasterService.create(orderMaster);
        Map<String, String> map=new HashMap<>();
        map.put("orderMasterId", result.getOrderId());
        return ResultVOUtil.success(map);
    }

    //create orderMaster list
    @GetMapping("/list")
    public ResultVO<List<OrderMaster>> list(@RequestParam("customerId") String customerId,
                                            @RequestParam(value = "page", defaultValue="0") Integer page,
                                            @RequestParam(value="size", defaultValue ="10") Integer size){
        if(StringUtils.hasLength(customerId)){
            log.error("【find OrderMaster】No customerId ");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request= PageRequest.of(page,size);
        Page<OrderMaster> orderMasterPage=orderMasterService.findList(customerId, request);

        return ResultVOUtil.success(orderMasterPage.getContent());

    }

    //create orderDetail
    @GetMapping("detail")
    public ResultVO<OrderMaster>detail(@RequestParam("customerId") String customerId,
                                       @RequestParam("orderId") String orderId){
        OrderMaster orderMaster=buyerService.findOrderOne(customerId, orderId);
        return ResultVOUtil.success(orderMaster);
    }

    //cancel orderMaster
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("customerId") String customerId,
                           @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(customerId, orderId);
        return ResultVOUtil.success();
    }
}
