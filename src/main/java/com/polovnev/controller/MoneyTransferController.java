package com.polovnev.controller;

import com.polovnev.exception.MoneyTransferIdIsNotExisted;
import com.polovnev.model.MoneyTransfer;
import com.polovnev.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.polovnev.constant.Constants.*;

@RestController
@RequestMapping(MONEY_TRANSFER_URL)
public class MoneyTransferController {

    @Autowired
    private MoneyTransferService moneyTransferService;

    @GetMapping(ID_PARAM)
    public MoneyTransfer getMoneyTransfer(@PathVariable long id, HttpServletResponse response,
                                          HttpSession httpSession) throws IOException {
        MoneyTransfer moneyTransfer = null;
        try {
            moneyTransfer = moneyTransferService.getMoneyTransfer(id);
        } catch (MoneyTransferIdIsNotExisted ex) {
            httpSession.setAttribute(EXCEPTION_ATTRIBUTE, ex);
            response.sendRedirect(ERROR_URL);
        }
        return moneyTransfer;
    }

    @PostMapping
    public MoneyTransfer addMoneyTransfer(@RequestParam(SOURCE_ACCOUNT_ID_REQUEST_PARAM) long sourceAccountId,
                                          @RequestParam(DESTINATION_ACCOUNT_ID_REQUEST_PARAM) long destinationAccountId,
                                          @RequestParam(SUM_REQUEST_PARAM) double sum,
                                          HttpServletResponse response,
                                          HttpSession httpSession) throws IOException {
        MoneyTransfer moneyTransfer = null;
        try {
            moneyTransfer = moneyTransferService.transferMoney(sourceAccountId, destinationAccountId, sum);
        } catch (Exception ex) {
            httpSession.setAttribute(EXCEPTION_ATTRIBUTE, ex);
            response.sendRedirect(ERROR_URL);
        }
        return moneyTransfer;
    }

}
