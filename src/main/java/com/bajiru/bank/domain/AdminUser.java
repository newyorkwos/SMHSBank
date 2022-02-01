package com.bajiru.bank.domain;

import com.bajiru.bank.enums.AdminUserCode;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description create admin user domain
 * @AuthorName StevenWu
 * @CreateDateTime 2022-01-12-9:48 PM
 */
@Entity
@Table
@DynamicUpdate
@Data
public class AdminUser {

    @Id
    private String id;
    private String username;
    private String realName;

    /*00:powerUser 01:Level1 user 02:Level2 user */
    private int level= AdminUserCode.L3USER.getCode();

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date changeTime;

    public AdminUser() {
    }
}
