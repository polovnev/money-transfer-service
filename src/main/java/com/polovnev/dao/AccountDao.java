package com.polovnev.dao;

import com.polovnev.exception.AccountIdIsNotExisted;
import com.polovnev.model.Account;

public interface AccountDao {

    Account getAccountById(long id) throws AccountIdIsNotExisted;

    void deleteAccountById(long id) throws AccountIdIsNotExisted;

}
