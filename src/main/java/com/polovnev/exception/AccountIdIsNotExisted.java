package com.polovnev.exception;

public class AccountIdIsNotExisted extends Exception {

    public AccountIdIsNotExisted(long id) {
        super("Account with id = " + id + " is not existed");
    }
}
