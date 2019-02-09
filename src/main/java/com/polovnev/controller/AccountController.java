package com.polovnev.controller;

import com.polovnev.exception.AccountIdIsNotExisted;
import com.polovnev.model.Account;
import com.polovnev.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
