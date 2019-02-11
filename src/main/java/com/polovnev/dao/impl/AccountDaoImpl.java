package com.polovnev.dao.impl;

import com.polovnev.dao.AccountDao;
import com.polovnev.exception.AccountIdIsNotExisted;
import com.polovnev.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.polovnev.constant.Constants.ACCOUNTS_BEAN;

@Component
public class AccountDaoImpl implements AccountDao {


    @Qualifier(value = ACCOUNTS_BEAN)
    @Autowired
    private List<Account> accounts;

    @Override
    public Account getAccountById(long id) throws AccountIdIsNotExisted {
            return accounts.stream().filter(account -> account.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> {return new AccountIdIsNotExisted(id);});
    }

    @Override
    public void deleteAccountById(long id) throws AccountIdIsNotExisted {
        Account account = getAccountById(id);
        accounts.remove(account);
    }

    @Override
    public long generateAccountId() {
        if(accounts.size() == 0) {
            return 0;
        }
        return accounts.stream().mapToLong(a -> a.getId()).max().getAsLong() + 1;
    }

    @Override
    public void addAccount(Account account) {
        accounts.add(account);
    }
}
