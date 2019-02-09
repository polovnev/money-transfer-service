package com.polovnev.model;

public class Account {
    private long id;
    private double balance;
    private Currency currency;

    public Account(long id, double balance, Currency currency) {
        this.id = id;
        this.balance = balance;
        this.currency = currency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
