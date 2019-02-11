package com.polovnev.exception;

public class BalanceIsNotEnough extends Exception {

    public BalanceIsNotEnough() {
        super("Balance is not enough for executing transaction");
    }
}
