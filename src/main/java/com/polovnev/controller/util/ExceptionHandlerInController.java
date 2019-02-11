package com.polovnev.controller.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static com.polovnev.constant.Constants.ERROR_URL;
import static com.polovnev.constant.Constants.EXCEPTION_ATTRIBUTE;

@Component
public class ExceptionHandlerInController {

    public void handleExceptionInController(Exception ex,
                                            HttpServletResponse response,
                                            HttpSession httpSession) {
        httpSession.setAttribute(EXCEPTION_ATTRIBUTE, ex);
        try {
            response.sendRedirect(ERROR_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
