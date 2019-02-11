package com.polovnev.controller;

import com.polovnev.exception.AccountIdIsNotExisted;
import com.polovnev.model.Account;
import com.polovnev.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.polovnev.constant.Constants.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping(ACCOUNT_URL)
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(ID_PARAM)
    public Account getAccount(@PathVariable long id, HttpServletResponse response,
                              HttpSession httpSession) throws IOException {
        Account account = null;
        try {
            account = accountService.getAccountById(id);
        } catch (AccountIdIsNotExisted ex) {
            httpSession.setAttribute(EXCEPTION_ATTRIBUTE, ex);
            response.sendRedirect(ERROR_URL);
        }
        return account;
    }

    @DeleteMapping(ID_PARAM)
    public String deleteAccount(@PathVariable long id, HttpServletResponse response,
                                HttpSession httpSession) throws IOException {
        try {
            accountService.removeAccount(id);
        } catch (AccountIdIsNotExisted ex) {
            httpSession.setAttribute(EXCEPTION_ATTRIBUTE, ex);
            response.sendRedirect(ERROR_URL);
        }
        return "Account with id = " + id + " was deleted";
    }

    @PutMapping(ID_PARAM)
    public String updateAccount(@PathVariable long id, @RequestParam(BALANCE_REQUEST_PARAM) double balance,
                                HttpServletResponse response,
                                HttpSession httpSession) throws IOException {
        try {
            accountService.increaseBalance(id, balance);
        } catch (AccountIdIsNotExisted ex) {
            httpSession.setAttribute(EXCEPTION_ATTRIBUTE, ex);
            response.sendRedirect(ERROR_URL);
        }
        return "Account with id = " + id + " was updated";
    }

    @PostMapping
    public Account addAccount(@RequestParam(BALANCE_REQUEST_PARAM) double balance,
                                @RequestParam(CURRENCY_REQUEST_PARAM) String currency) {
        return accountService.addAccount(balance, currency);
    }

}
