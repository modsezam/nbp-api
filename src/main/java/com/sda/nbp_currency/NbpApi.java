package com.sda.nbp_currency;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.sda.nbp_currency.model.Currency;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.Scanner;

public class NbpApi {


    String generatePathToNbpApi(String table, LocalDate date){
        String datePut = "";
        if (date != null){
            datePut = date.toString() + "/";
        }
        return "http://api.nbp.pl/api/exchangerates/tables/"+ table +"/"+ datePut +"?format=json";
    }

    String getJsonFromNbpApi(final String patch) throws IOException {
        URL url = new URL(patch);
        URLConnection connection = url.openConnection();
        InputStream input = connection.getInputStream();

        Scanner scanner = new Scanner(input);
        return scanner.nextLine();
    }

    Currency[] convertJsonToObject(String json) throws JsonSyntaxException {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, Currency[].class);
    }



}
