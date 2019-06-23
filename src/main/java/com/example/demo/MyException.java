package com.example.demo;

public class MyException extends Exception {

    private static final long serialVersionUID = 1L;
    private final String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public MyException(String errorMsg) {
        super();
        this.errorMsg = errorMsg;
    }

}
