package com.polovnev.service;

import com.polovnev.exception.UnsupportedAccountCurrencyException;
import com.polovnev.model.Account;

public interface MoneyTransferService {

    String transferMoney(Account fromAccount, Account toAccount, double sum) throws UnsupportedAccountCurrencyException;

}
