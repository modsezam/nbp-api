package com.sda.nbp_currency.model;

import java.util.List;

public class Currency {

    private String effectiveDate;
    private List<Rates> rates;

    public List<Rates> getRates() {
        return rates;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

}

