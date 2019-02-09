package com.polovnev.service.impl;

import com.polovnev.exception.UnsupportedAccountCurrencyException;
import com.polovnev.model.Account;
import com.polovnev.service.MoneyTransferService;
import org.springframework.stereotype.Service;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {
    @Override
    public String transferMoney(Account fromAccount, Account toAccount, double sum) throws UnsupportedAccountCurrencyException {
        return null;
    }
}
