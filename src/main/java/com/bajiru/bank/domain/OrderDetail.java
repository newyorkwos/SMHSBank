package com.bajiru.bank.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by 廖师兄
 * 2017-06-11 17:20
 */
@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;

    /** 订单id. */
    @JsonIgnore
    @ManyToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    private OrderMaster orderMaster;

    /** 商品id. */
    @ManyToOne
    private ProductInfo productInfo;

    /** 商品数量. */
    private Integer productQuantity;

    public OrderDetail() {
    }
}
