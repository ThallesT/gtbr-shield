package com.gtbr.gtbrshield.entity.enums;

import lombok.Getter;

@Getter
public enum StandardStatusEnum {

    AVAILABLE(1, "Available"),
    UNAVAILABLE(2, "Unavailable");

    private Integer code;
    private String description;

    StandardStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
