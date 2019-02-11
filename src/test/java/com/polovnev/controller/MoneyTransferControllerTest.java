package com.polovnev.controller;

import com.google.gson.Gson;
import com.polovnev.model.Account;
import com.polovnev.model.MoneyTransfer;
import com.polovnev.model.MoneyTransferStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.polovnev.constant.Constants.*;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MoneyTransferControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Gson gson;

    @Test
    public void getMoneyTransfer() throws Exception {
        final int accountId = 0;
        final String expectedContent = "\"id\":" + accountId;
        final int errorAccountId = -1;
        mvc.perform(get(MONEY_TRANSFER_URL + "/" + accountId))
                .andExpect(status().isOk()).andExpect(content().string(containsString(expectedContent)));
        mvc.perform(get(MONEY_TRANSFER_URL + "/" + errorAccountId))
                .andExpect(redirectedUrl(ERROR_URL));
    }


    @Test
    public void createMoneyTransfer() throws Exception {
        final long sourceAccountId = 0, destinationAccountId = 2;
        final double sum = 10000.0;
        double sourceAccountBalanceBeforeTransfer, destinationAccountBalanceBeforeTransfer,
                sourceAccountBalanceAfterTransfer, destinationAccountBalanceAfterTransfer;

        String responseSourceAccountBeforeTransfer = mvc.perform(get(ACCOUNT_URL + "/" + sourceAccountId))
                .andReturn().getResponse().getContentAsString();

        Account sourceAccountBeforeTransfer = gson.fromJson(responseSourceAccountBeforeTransfer, Account.class);
        sourceAccountBalanceBeforeTransfer = sourceAccountBeforeTransfer.getBalance();

        String responseDestinationAccountBeforeTransfer = mvc.perform(get(ACCOUNT_URL + "/" + destinationAccountId))
                .andReturn().getResponse().getContentAsString();

        Account destinationAccountBeforeTransfer = gson.fromJson(responseDestinationAccountBeforeTransfer, Account.class);
        destinationAccountBalanceBeforeTransfer = destinationAccountBeforeTransfer.getBalance();

        String responseString = mvc.perform(post(MONEY_TRANSFER_URL)
                .param(SOURCE_ACCOUNT_ID_REQUEST_PARAM, sourceAccountId + "")
                .param(DESTINATION_ACCOUNT_ID_REQUEST_PARAM, destinationAccountId + "")
                .param(SUM_REQUEST_PARAM, sum + ""))
                .andReturn().getResponse().getContentAsString();

        MoneyTransfer moneyTransfer = gson.fromJson(responseString, MoneyTransfer.class);
        long moneyTransferId = moneyTransfer.getId();

        mvc.perform(get(MONEY_TRANSFER_URL + "/" + moneyTransferId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(MoneyTransferStatus.DONE.name())));

        String responseSourceAccountAfterTransfer = mvc.perform(get(ACCOUNT_URL + "/" + sourceAccountId))
                .andReturn().getResponse().getContentAsString();

        Account sourceAccountAfterTransfer = gson.fromJson(responseSourceAccountAfterTransfer, Account.class);
        sourceAccountBalanceAfterTransfer = sourceAccountAfterTransfer.getBalance();

        String responseDestinationAccountAfterTransfer = mvc.perform(get(ACCOUNT_URL + "/" + destinationAccountId))
                .andReturn().getResponse().getContentAsString();

        Account destinationAccountAfterTransfer = gson.fromJson(responseDestinationAccountAfterTransfer, Account.class);
        destinationAccountBalanceAfterTransfer = destinationAccountAfterTransfer.getBalance();

        assertEquals(sourceAccountBalanceBeforeTransfer - sum, sourceAccountBalanceAfterTransfer,0);
        assertEquals(destinationAccountBalanceBeforeTransfer + sum, destinationAccountBalanceAfterTransfer,0);
    }

    @Test
    public void unrealSumMoneyTransfer() throws Exception {
        final long sourceAccountId = 0, destinationAccountId = 2;
        final double sum = Double.MAX_VALUE;

        mvc.perform(post(MONEY_TRANSFER_URL)
                .param(SOURCE_ACCOUNT_ID_REQUEST_PARAM, sourceAccountId + "")
                .param(DESTINATION_ACCOUNT_ID_REQUEST_PARAM, destinationAccountId + "")
                .param(SUM_REQUEST_PARAM, sum + ""))
                .andExpect(redirectedUrl(ERROR_URL));

    }
}
