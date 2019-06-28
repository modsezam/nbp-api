package com.sda.nbp_currency;

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
        currencyAverageValue.printCurrentAverageExchange(dateCurrent, "USD", "EUR", "GBP", "CHF");

        CurrencyValueDifference currencyValueDifference = new CurrencyValueDifference(dateReadFromCurrentAverageJson, dateOfPreviousExchange);
        currencyValueDifference.printDifferenceCurrencyExchange(dateOfPreviousExchange, "USD", "EUR", "GBP", "CHF");

    }


}
