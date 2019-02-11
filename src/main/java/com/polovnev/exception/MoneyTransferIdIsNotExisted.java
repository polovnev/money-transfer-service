package com.polovnev.exception;

public class MoneyTransferIdIsNotExisted extends Exception {
    public MoneyTransferIdIsNotExisted(long id) {
        super("Transfer with id = " + id + " is not existed");
    }
}
