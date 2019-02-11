package com.polovnev.service;

import com.polovnev.exception.AccountIdIsNotExisted;
import com.polovnev.model.Account;

public interface AccountService {

    Account addAccount(double balance, String currency);

    Account getAccountById(long id) throws AccountIdIsNotExisted;

    Account increaseBalance(long id, double addedMoney) throws AccountIdIsNotExisted;

    void removeAccount(long id) throws AccountIdIsNotExisted;

}
