package com.sda.nbp_currency.model;

import java.util.List;

public class Currency {

    private String effectiveDate;
    private List<Rate> rates;

    public List<Rate> getRates() {
        return rates;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

}

