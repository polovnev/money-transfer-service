package com.polovnev.service.impl;

import com.polovnev.dao.AccountDao;
import com.polovnev.dao.MoneyTransferDao;
import com.polovnev.exception.AccountIdIsNotExisted;
import com.polovnev.exception.BalanceIsNotEnough;
import com.polovnev.exception.MoneyTransferIdIsNotExisted;
import com.polovnev.exception.UnsupportedAccountCurrencyException;
import com.polovnev.model.Account;
import com.polovnev.model.Currency;
import com.polovnev.model.MoneyTransfer;
import com.polovnev.model.MoneyTransferStatus;
import com.polovnev.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private MoneyTransferDao moneyTransferDao;

    @Override
    public MoneyTransfer getMoneyTransfer(long id) throws MoneyTransferIdIsNotExisted {
       return moneyTransferDao.getMoneyTransferById(id);
    }

    @Override
    public MoneyTransfer transferMoney(long fromAccountId, long toAccountId, double sum)
            throws UnsupportedAccountCurrencyException, AccountIdIsNotExisted, BalanceIsNotEnough {
        Account sourceAccount = accountDao.getAccountById(fromAccountId);
        Account destinationAccount = accountDao.getAccountById(toAccountId);
        Currency fromAccountCurrency = sourceAccount.getCurrency();
        Currency toAccountCurrency = destinationAccount.getCurrency();
        if (fromAccountCurrency != toAccountCurrency) {
            throw new UnsupportedAccountCurrencyException(fromAccountCurrency, toAccountCurrency);
        }
        long id = moneyTransferDao.generateId();
        MoneyTransfer moneyTransfer = new MoneyTransfer(id, sourceAccount, destinationAccount, sum);
        moneyTransferDao.addMoneyTransfer(moneyTransfer);
        return implementMoneyTransfer(moneyTransfer);
    }

    synchronized private MoneyTransfer implementMoneyTransfer(MoneyTransfer moneyTransfer) throws BalanceIsNotEnough {
        Account sourceAccount = moneyTransfer.getSourceAccount();
        Account destinationAccount = moneyTransfer.getDestinationAccount();
        double sum = moneyTransfer.getSum();
        double sourceAccountBalance = sourceAccount.getBalance();
        double destinationAccountBalance = destinationAccount.getBalance();
        if(moneyTransfer.getSum() > sourceAccountBalance) {
            moneyTransfer.setMoneyTransferStatus(MoneyTransferStatus.ABORTED);
            throw new BalanceIsNotEnough();
        }
        sourceAccountBalance -= sum;
        destinationAccountBalance += sum;
        sourceAccount.setBalance(sourceAccountBalance);
        destinationAccount.setBalance(destinationAccountBalance);
        moneyTransfer.setMoneyTransferStatus(MoneyTransferStatus.DONE);
        return moneyTransfer;
    }
}
