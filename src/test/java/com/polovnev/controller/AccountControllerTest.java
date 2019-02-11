package com.polovnev.controller;

import com.google.gson.Gson;
import com.polovnev.constant.Constants;
import com.polovnev.model.Account;

import static org.junit.Assert.*;

import com.polovnev.model.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;
import static com.polovnev.constant.Constants.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Gson gson;

    @Test
    public void getAccount() throws Exception {
        final int accountId = 0;
        final String expectedContent = "\"id\":" + accountId;
        final int errorAccountId = -1;
        mvc.perform(get(ACCOUNT_URL + "/" + accountId))
                .andExpect(status().isOk()).andExpect(content().string(containsString(expectedContent)));
        mvc.perform(get(ACCOUNT_URL + "/" + errorAccountId))
                .andExpect(redirectedUrl(ERROR_URL));
    }

    @Test
    public void deleteAccount() throws Exception {
        final int accountId = 1;
        mvc.perform(delete(ACCOUNT_URL + "/" + accountId))
                .andExpect(status().isOk());
        mvc.perform(get(ACCOUNT_URL + "/" + accountId))
                .andExpect(redirectedUrl(ERROR_URL));
    }

    @Test
    public void increaseAccountBalance() throws Exception {
        final int accountId = 2;
        final double balance = 500.0;
        double balanceBeforeUpdate, balanceAfterUpdate;
        String responseStringBeforeUpdate = mvc.perform(get(ACCOUNT_URL + "/" + accountId))
                .andReturn().getResponse().getContentAsString();

        Account accountBeforeUpdate = gson.fromJson(responseStringBeforeUpdate, Account.class);
        balanceBeforeUpdate = accountBeforeUpdate.getBalance();

        mvc.perform(put(ACCOUNT_URL + "/" + accountId)
                .param(BALANCE_REQUEST_PARAM, balance + ""));

        String responseStringAfterUpdate = mvc.perform(get(ACCOUNT_URL + "/" + accountId))
                .andReturn().getResponse().getContentAsString();
        Account accountAfterUpdate = gson.fromJson(responseStringAfterUpdate, Account.class);
        balanceAfterUpdate = accountAfterUpdate.getBalance();

        assertEquals(balanceBeforeUpdate + balance, balanceAfterUpdate, 0);
    }

    @Test
    public void createAccount() throws Exception {
        final double balance = 100000.0;
        final String currency = Currency.PLN.name();
        String responseString = mvc.perform(post(ACCOUNT_URL)
                .param(BALANCE_REQUEST_PARAM, balance + "")
                .param(CURRENCY_REQUEST_PARAM, currency))
                .andReturn().getResponse().getContentAsString();

        Account account = gson.fromJson(responseString, Account.class);
        long accountId = account.getId();

        mvc.perform(get(ACCOUNT_URL + "/" + accountId))
                .andExpect(status().isOk()).andExpect(content().string(containsString(responseString)));
    }

}
