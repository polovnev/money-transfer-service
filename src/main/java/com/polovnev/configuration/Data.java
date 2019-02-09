package com.polovnev.configuration;

import com.polovnev.model.Account;
import com.polovnev.model.Currency;
import com.polovnev.model.MoneyTransfer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import static com.polovnev.constant.Constants.*;

@Configuration
public class Data {

    @Bean(name = ACCOUNTS_BEAN)
    public List<Account> getAccounts() {
        return new ArrayList<Account>() {
            {
                add(new Account(0,100000, Currency.PLN));
                add(new Account(1,100000, Currency.PLN));
                add(new Account(2,100000, Currency.PLN));
                add(new Account(3,100000, Currency.EUR));
                add(new Account(4,100000, Currency.EUR));
                add(new Account(5,100000, Currency.EUR));
                add(new Account(6,100000, Currency.EUR));
                add(new Account(7,100000, Currency.USD));
                add(new Account(8,100000, Currency.USD));
                add(new Account(9,100000, Currency.USD));

            }

        };
    }

    @Bean(name = MONEY_TRANSFERS_BEAN)
    public List<MoneyTransfer> getMoneyTransfer() {
        return new ArrayList<>();
    }

}
