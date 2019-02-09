package com.polovnev.model;

import static com.polovnev.constant.Constants.*;

public enum Currency {

    PLN(PLN_CURRENCY), EUR(EUR_CURRENCY), USD(USD_CURRENCY);

    private String currencyName;

    Currency(String currencyName) {
        this.currencyName = currencyName;
    }

    @Override
    public String toString() {
        return currencyName;
    }
}
