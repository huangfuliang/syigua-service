package com.syigua.constant;

public enum ModelEnum {

    USER("user"),

    ASSISTANT("assistant") ,
    SYSTEM("system");

    private String value;

    ModelEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
