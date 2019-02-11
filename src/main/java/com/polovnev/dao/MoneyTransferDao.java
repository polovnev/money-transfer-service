package com.polovnev.dao;

import com.polovnev.exception.MoneyTransferIdIsNotExisted;
import com.polovnev.model.MoneyTransfer;

public interface MoneyTransferDao {

    long generateId();

    MoneyTransfer getMoneyTransferById(long id) throws MoneyTransferIdIsNotExisted;

    MoneyTransfer addMoneyTransfer(MoneyTransfer moneyTransfer);
}
