package com.sda.nbp_currency;

import java.time.LocalDate;
import java.util.Map;

public class CurrencyValueDifference {

    private LocalDate dateReadFromJson;
    private Map<String, Double> currentSalesExchangeMap;
    private Map<String, Double> previousPurchaseExchangeMap;

    public CurrencyValueDifference(LocalDate dateReadFromCurrentJson, LocalDate dateOfPreviousExchange) {
        CurrencyValue currentSalesCurrencyValue = new CurrencyValue();
        currentSalesExchangeMap = currentSalesCurrencyValue.getCurrentExchange("c", dateReadFromCurrentJson, "getBid", true);
        CurrencyValue previousPurchaseCurrencyValue = new CurrencyValue();
        previousPurchaseExchangeMap = previousPurchaseCurrencyValue.getCurrentExchange("c", dateOfPreviousExchange, "getAsk", true);
        dateReadFromJson = previousPurchaseCurrencyValue.getDateReadFromJson();
    }

    void printDifferenceCurrencyExchange(LocalDate dateOfPreviousExchange, String... args) {

        System.out.println("\nProfit from exchange " + Main.CURRENCY_PLN + " PLN in " + dateOfPreviousExchange + ":");
        if (!dateOfPreviousExchange.equals(dateReadFromJson)) {
            System.out.println("In " + dateOfPreviousExchange + " there are no currency exchanges, the closest date was chosen to: " + dateReadFromJson);
        }

         for (String arg : args) {
             arg = arg.toUpperCase();
             if (previousPurchaseExchangeMap.get(arg) != null){
                 System.out.println("Profit from buying "+ arg +" a " + Main.NUMBER_OF_MONTHS + " month ago is: " +
                         (Math.round(Main.CURRENCY_PLN * 100 / previousPurchaseExchangeMap.get(arg) * currentSalesExchangeMap.get(arg) - Main.CURRENCY_PLN * 100) / 100.0) + " PLN");
             } else {
                 System.out.println("There is no " + arg + " currency");
             }
        }
    }

}
