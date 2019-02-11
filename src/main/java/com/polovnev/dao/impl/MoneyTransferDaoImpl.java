package com.polovnev.dao.impl;

import com.polovnev.dao.MoneyTransferDao;
import com.polovnev.exception.MoneyTransferIdIsNotExisted;
import com.polovnev.model.MoneyTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.polovnev.constant.Constants.MONEY_TRANSFERS_BEAN;

@Component
public class MoneyTransferDaoImpl implements MoneyTransferDao {

    @Qualifier(value = MONEY_TRANSFERS_BEAN)
    @Autowired
    private List<MoneyTransfer> moneyTransfers;


    @Override
    public long generateId() {
        if(moneyTransfers.size() == 0) {
            return 0;
        }
        return moneyTransfers.stream().mapToLong(moneyTransfer -> moneyTransfer.getId()).max().getAsLong() + 1;
    }

    @Override
    public MoneyTransfer getMoneyTransferById(long id) throws MoneyTransferIdIsNotExisted {
        return moneyTransfers.stream().filter(moneyTransfer -> moneyTransfer.getId() == id)
                .findFirst()
                .orElseThrow(() -> {return new MoneyTransferIdIsNotExisted(id);});    }

    @Override
    public MoneyTransfer addMoneyTransfer(MoneyTransfer moneyTransfer) {
        moneyTransfers.add(moneyTransfer);
        return moneyTransfer;
    }
}
