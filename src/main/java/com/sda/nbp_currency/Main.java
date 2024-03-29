package com.sda.nbp_currency;

import com.sda.nbp_currency.model.CurrencyCode;

import java.time.LocalDate;

public class Main {

    static final double CURRENCY_PLN = 100;
    static final int NUMBER_OF_MONTHS = 1;

    public static void main(String[] args) {

        LocalDate dateCurrent = LocalDate.now();
        LocalDate dateOfPreviousExchange = LocalDate.now().minusMonths(Main.NUMBER_OF_MONTHS);
        LocalDate dateReadFromCurrentAverageJson;

        CurrencyValueAverage currencyAverageValue = new CurrencyValueAverage();
        dateReadFromCurrentAverageJson = currencyAverageValue.getDateReadFromJson();
        currencyAverageValue.printCurrentAverageExchange(dateCurrent,
                CurrencyCode.USD.getCode(),
                CurrencyCode.EUR.getCode(),
                CurrencyCode.GBP.getCode(),
                CurrencyCode.CHF.getCode());

        CurrencyValueDifference currencyValueDifference = new CurrencyValueDifference(dateReadFromCurrentAverageJson, dateOfPreviousExchange);
        currencyValueDifference.printDifferenceCurrencyExchange(dateOfPreviousExchange,
                CurrencyCode.USD.getCode(),
                CurrencyCode.EUR.getCode(),
                CurrencyCode.GBP.getCode(),
                CurrencyCode.CHF.getCode());

    }


}
