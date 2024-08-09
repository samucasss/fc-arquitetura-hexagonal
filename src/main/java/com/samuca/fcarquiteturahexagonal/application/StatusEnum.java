package com.samuca.fcarquiteturahexagonal.application;

import lombok.Getter;

public enum StatusEnum {
    ENABLED("enabled"),
    DISABLED("disabled");

    @Getter
    private final String status;

    StatusEnum(String status){
        this.status = status;
    }
}
