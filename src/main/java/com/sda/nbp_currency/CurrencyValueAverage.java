package com.sda.nbp_currency;

import java.time.LocalDate;
import java.util.Map;

public class CurrencyValueAverage {

    private LocalDate dateReadFromJson;
    private CurrencyValue averageCurrencyValue = new CurrencyValue();
    private Map<String, Double> averageCurrencyMap;

    public CurrencyValueAverage() {
        averageCurrencyMap = averageCurrencyValue.getCurrentExchange("a", null, "getMid", false);
        dateReadFromJson = averageCurrencyValue.getDateReadFromJson();
    }

    public LocalDate getDateReadFromJson() {
        return dateReadFromJson;
    }

    void printCurrentAverageExchange(final LocalDate dateCurrent, String... args) {

        System.out.println("Data from: " + dateReadFromJson);

        if (!dateReadFromJson.equals(dateCurrent)) {
            System.out.println("The date read is different from the current one!");
        }

        for (String arg : args) {
            System.out.println("The current average USD exchange rate is: " + averageCurrencyMap.get(arg) + ", " + Main.CURRENCY_PLN + " PLN is worth "
                    + Math.round(Main.CURRENCY_PLN * 100 / averageCurrencyMap.get(arg)) / 100.0 + " " + arg);
        }
    }

}
