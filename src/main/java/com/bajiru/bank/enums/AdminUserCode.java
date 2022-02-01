package com.bajiru.bank.enums;

import lombok.Getter;

/**
 * @Description create adminUser type code
 * @AuthorName StevenWu
 * @CreateDateTime 2022-01-12-10:11 PM
 */
@Getter
public  enum AdminUserCode implements CodeEnum{

    POWERUSER(0, "powerUser"),
        L1USER(1,"LEVEL 1 USER"),
        L2USER(2,"LEVEL 2 USER"),
        L3USER(3,"LEVEL 3 USER");


    private Integer code;
    private String massage;
    AdminUserCode(Integer code, String message){
        this.code=code;
        this.massage=message;
    }
}
