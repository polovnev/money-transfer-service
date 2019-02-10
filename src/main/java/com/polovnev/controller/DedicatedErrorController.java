package com.polovnev.controller;

import static com.polovnev.constant.Constants.*;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class DedicatedErrorController implements ErrorController {

    @ResponseBody
    @RequestMapping(ERROR_URL)
    public String handleException(HttpSession httpSession) {
        Exception exception = (Exception) httpSession.getAttribute(EXCEPTION_ATTRIBUTE);
        return exception.getMessage();
    }

    @Override
    public String getErrorPath() {
        return ERROR_URL;
    }
}
