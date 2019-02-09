package com.polovnev.dao.impl;

import com.polovnev.dao.MoneyTransferDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

import static com.polovnev.constant.Constants.MONEY_TRANSFERS_BEAN;

public class MoneyTransferDaoImpl implements MoneyTransferDao {

    @Qualifier(value = MONEY_TRANSFERS_BEAN)
    @Autowired
    private List<MoneyTransferDao> moneyTransfers;


}
