package com.gtbr.gtbrshield.entity.enums;

import lombok.Getter;

@Getter
public enum UserStatusEnum {

    ACTIVE(1, "User is active and allowed"),
    INACTIVE(2, "User isn't active but is allowed"),
    FIRST_TIME_LOGIN(3 ,"First login, password is auto-generated"),
    BANNED(4, "User banned");

    private Integer code;
    private String description;

    UserStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
