package com.sda.nbp_currency;

import com.google.gson.JsonSyntaxException;
import com.sda.nbp_currency.model.Currency;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CurrencyValue {

    private Currency[] currencies;
    private Map<String, Double> currenciesMap = new HashMap<>();
    private NbpApi nbpApi = new NbpApi();
    private LocalDate dateReadFromJson;


    public LocalDate getDateReadFromJson() {
        return dateReadFromJson;
    }

    Map<String, Double> getCurrentExchange(String table, LocalDate date, String getMarker, boolean findPreviousDate) throws JsonSyntaxException {

        String jsonCurrentAverageExchange = null;
        LocalDate dateTemp = date;

        do {
            try {
                jsonCurrentAverageExchange = nbpApi.getJsonFromNbpApi(nbpApi.generatePathToNbpApi(table, date));
                findPreviousDate = false;
            } catch (IOException e) {
                if (!findPreviousDate){
                    System.out.println("No connection witch the api NBP: " + e);
                    System.out.println("Check your connection!");
                    System.exit(0);
                } else {
                    date = date.minusDays(1);
                    if (dateTemp.minusDays(10).isEqual(date)) {
                        System.out.println("Error problem with NBP Api!");
                        System.exit(0);
                    }
                }
            }
        } while (findPreviousDate);

        try {
            currencies = nbpApi.convertJsonToObject(jsonCurrentAverageExchange);
        } catch (JsonSyntaxException e) {
            System.out.println("Error problem with format json!");
            System.out.println(e.getLocalizedMessage());
            System.exit(0);
            return null;
        }

        String effectiveDate = currencies[0].getEffectiveDate();
        dateReadFromJson = LocalDate.parse(effectiveDate);

        return fillTheMapWithCurrencyExchanged(getMarker);

    }

    private Map<String, Double> fillTheMapWithCurrencyExchanged(String getMarker) {

        switch (getMarker) {
            case "getMid":
                currencies[0].getRates()
                        .forEach(rates -> currenciesMap.put(rates.getCode(), rates.getMid()));
                break;
            case "getBid":
                currencies[0].getRates()
                        .forEach(rates -> currenciesMap.put(rates.getCode(), rates.getBid()));
                break;
            case "getAsk":
                currencies[0].getRates()
                        .forEach(rates -> currenciesMap.put(rates.getCode(), rates.getAsk()));
                break;
        }
        return currenciesMap;

    }


}
