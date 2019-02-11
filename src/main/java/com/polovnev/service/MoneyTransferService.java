package com.polovnev.service;

import com.polovnev.exception.AccountIdIsNotExisted;
import com.polovnev.exception.BalanceIsNotEnough;
import com.polovnev.exception.MoneyTransferIdIsNotExisted;
import com.polovnev.exception.UnsupportedAccountCurrencyException;
import com.polovnev.model.MoneyTransfer;

public interface MoneyTransferService {

    MoneyTransfer getMoneyTransfer(long id) throws MoneyTransferIdIsNotExisted;

    MoneyTransfer transferMoney(long fromAccountId, long toAccountId, double sum)
            throws UnsupportedAccountCurrencyException, AccountIdIsNotExisted, BalanceIsNotEnough;

}
