package com.sda.nbp_currency.model;

public enum CurrencyCode {

    USD("USD"),
    AUD("AUD"),
    CAD("CAD"),
    EUR("EUR"),
    HUF("HUF"),
    CHF("CHF"),
    GBP("GBP"),
    JPY("JPY"),
    CZK("CZK"),
    DKK("DKK"),
    NOK("NOK"),
    SEK("SEK"),
    XDR("XDR");

    private String code;

    CurrencyCode(final String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
