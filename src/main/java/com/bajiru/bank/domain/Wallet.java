package com.bajiru.bank.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description create person wallet
 * @AuthorName StevenWu
 * @CreateDateTime 2022-01-15-7:56 PM
 */
@Entity
@Data
@DynamicUpdate
public class Wallet {
    @Id
    private String walletId;
    private BigDecimal money;
    @OneToOne
    private Customer customer;

    /*0:Normal 1:prohibit 2:close*/
    private int status;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;

    public Wallet() {

    }
}
