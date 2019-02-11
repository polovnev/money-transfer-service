package com.polovnev.controller;

import com.polovnev.controller.util.ExceptionHandlerInController;
import com.polovnev.exception.MoneyTransferIdIsNotExisted;
import com.polovnev.model.MoneyTransfer;
import com.polovnev.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.polovnev.constant.Constants.*;

@RestController
@RequestMapping(MONEY_TRANSFER_URL)
public class MoneyTransferController {

    @Autowired
    private MoneyTransferService moneyTransferService;

    @Autowired
    private ExceptionHandlerInController exceptionHandlerInController;

    @GetMapping(ID_PARAM)
    public MoneyTransfer getMoneyTransfer(@PathVariable long id, HttpServletResponse response,
                                          HttpSession httpSession){
        MoneyTransfer moneyTransfer = null;
        try {
            moneyTransfer = moneyTransferService.getMoneyTransfer(id);
        } catch (MoneyTransferIdIsNotExisted ex) {
            exceptionHandlerInController.handleExceptionInController(ex, response, httpSession);
        }
        return moneyTransfer;
    }

    @PostMapping
    public MoneyTransfer addMoneyTransfer(@RequestParam(SOURCE_ACCOUNT_ID_REQUEST_PARAM) long sourceAccountId,
                                          @RequestParam(DESTINATION_ACCOUNT_ID_REQUEST_PARAM) long destinationAccountId,
                                          @RequestParam(SUM_REQUEST_PARAM) double sum,
                                          HttpServletResponse response,
                                          HttpSession httpSession){
        MoneyTransfer moneyTransfer = null;
        try {
            moneyTransfer = moneyTransferService.transferMoney(sourceAccountId, destinationAccountId, sum);
        } catch (Exception ex) {
            exceptionHandlerInController.handleExceptionInController(ex, response, httpSession);
        }
        return moneyTransfer;
    }

}
