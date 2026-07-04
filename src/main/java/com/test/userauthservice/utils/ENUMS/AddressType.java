package com.test.userauthservice.utils.ENUMS;

import lombok.Getter;

@Getter
public enum AddressType {

    HOME("HOME"),
    WORK("WORK"),
    OTHER("OTHER");

    private final String value;

    AddressType(String value) {
        this.value = value;
    }
}