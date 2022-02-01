package com.bajiru.bank.enums;

import lombok.Getter;

/**
 * @Description create wallet enum
 * @AuthorName StevenWu
 * @CreateDateTime 2022-01-15-8:03 PM
 */
@Getter
public enum WalletEnum implements CodeEnum{

    NORMAL(0,"normal"),
    PROHIBIT(1,"prohibit"),
    CLOSED(2,"closed");

    private Integer code;
    private String message;

    WalletEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
