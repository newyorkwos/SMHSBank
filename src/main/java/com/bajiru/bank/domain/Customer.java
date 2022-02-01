package com.bajiru.bank.domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description create customer domain
 * @AuthorName StevenWu
 * @CreateDateTime 2022-01-12-9:47 PM
 */
@Entity
@DynamicUpdate
@Data
public class Customer {

    @Id
    private String id;
    private String username;
    private String lastname;
    private String middleName;
    private String firstName;

    private String phoneNumber;
    private String address;
    private String email;
    private String password;

    @OneToOne
    private Wallet wallet;


    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @ToString.Exclude
    private Set<OrderMaster> orderMasterSet=new HashSet<>();


    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;

    public Customer() {
    }
}
