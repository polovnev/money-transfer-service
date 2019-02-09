package com.polovnev.exception;

import com.polovnev.model.Currency;

public class UnsupportedAccountCurrencyException extends Exception {

    public UnsupportedAccountCurrencyException(Currency currencyFrom, Currency currencyTo) {
        super("The service does not support transaction from " + currencyFrom + "account to " + currencyTo + "account");
    }
}
