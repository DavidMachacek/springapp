package com.david.demo.errorHandling;

public enum ErrorCodes {

    ERR_001_MANDATORY("ERR_001_MANDATORY"),
    ERR_002_BAD_VALUE("ERR_002_BAD_VALUE");

    private final String value;
    ErrorCodes(String value) { this.value = value; }
    public String getValue() { return value; }
}
