package com.polovnev.service.impl;

import com.polovnev.dao.AccountDao;
import com.polovnev.exception.AccountIdIsNotExisted;
import com.polovnev.model.Account;
import com.polovnev.model.Currency;
import com.polovnev.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Account addAccount(double balance, String currencyString) {
        long id = accountDao.generateAccountId();
        Currency currency = Currency.valueOf(currencyString);
        Account account = new Account(id, balance, currency);
        accountDao.addAccount(account);
        return account;
    }

    @Override
    public Account getAccountById(long id) throws AccountIdIsNotExisted {
        return accountDao.getAccountById(id);
    }

    @Override
    public Account increaseBalance(long id, double addedMoney) throws AccountIdIsNotExisted {
        Account account = accountDao.getAccountById(id);
        double balance = account.getBalance();
        balance += addedMoney;
        account.setBalance(balance);
        return account;
    }

    @Override
    public void removeAccount(long id) throws AccountIdIsNotExisted {
        accountDao.deleteAccountById(id);
    }
}
