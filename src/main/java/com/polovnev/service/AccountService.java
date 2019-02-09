package com.polovnev.service;

import com.polovnev.exception.AccountIdIsNotExisted;
import com.polovnev.model.Account;
import com.polovnev.model.Currency;

public interface AccountService {

    void addAccount(double balance, Currency currency);

    Account getAccountById(long id) throws AccountIdIsNotExisted;

    Account increaseBalance(long id, double addedMoney) throws AccountIdIsNotExisted;

    void removeAccount(long id) throws AccountIdIsNotExisted;

}
