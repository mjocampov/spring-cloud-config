package com.mjocampo.ConfigClient.service;

public class InvalidAccountBalanceException extends RuntimeException{

    private static final long serialVersionUID = 1;

    public InvalidAccountBalanceException(String msg) {
        super(msg);
    }
}
