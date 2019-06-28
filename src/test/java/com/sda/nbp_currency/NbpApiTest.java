package com.sda.nbp_currency;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class NbpApiTest {

    private NbpApi nbpApi;

    @Before
    public void init() {
        nbpApi = new NbpApi();
    }

    @Test
    public void getJsonFromNbpApiShouldConnectToNbpApiAndDownloadCorrectJsonFile() throws IOException {
        String patch = "http://api.nbp.pl/api/exchangerates/tables/c/2019-06-24/?format=json";
        String expected = "[{\"table\":\"C\",\"no\":\"120/C/NBP/2019\",\"tradingDate\":\"2019-06-21\",\"effectiveDate\":\"2019-06-24\",\"rates\":[{\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"bid\":3.7194,\"ask\":3.7946},{\"currency\":\"dolar australijski\",\"code\":\"AUD\",\"bid\":2.5701,\"ask\":2.6221},{\"currency\":\"dolar kanadyjski\",\"code\":\"CAD\",\"bid\":2.8149,\"ask\":2.8717},{\"currency\":\"euro\",\"code\":\"EUR\",\"bid\":4.2128,\"ask\":4.2980},{\"currency\":\"forint (Węgry)\",\"code\":\"HUF\",\"bid\":0.012993,\"ask\":0.013255},{\"currency\":\"frank szwajcarski\",\"code\":\"CHF\",\"bid\":3.7887,\"ask\":3.8653},{\"currency\":\"funt szterling\",\"code\":\"GBP\",\"bid\":4.7150,\"ask\":4.8102},{\"currency\":\"jen (Japonia)\",\"code\":\"JPY\",\"bid\":0.034571,\"ask\":0.035269},{\"currency\":\"korona czeska\",\"code\":\"CZK\",\"bid\":0.1644,\"ask\":0.1678},{\"currency\":\"korona duńska\",\"code\":\"DKK\",\"bid\":0.5643,\"ask\":0.5757},{\"currency\":\"korona norweska\",\"code\":\"NOK\",\"bid\":0.4351,\"ask\":0.4439},{\"currency\":\"korona szwedzka\",\"code\":\"SEK\",\"bid\":0.3960,\"ask\":0.4040},{\"currency\":\"SDR (MFW)\",\"code\":\"XDR\",\"bid\":5.1697,\"ask\":5.2741}]}]";

        String result = nbpApi.getJsonFromNbpApi(patch);

        assertEquals(expected, result);
    }

    @Test
    public void convertJsonToObjectShouldCorrectlyConvertJsonToObject() {
        String json = "[{\"table\":\"C\",\"no\":\"120/C/NBP/2019\",\"tradingDate\":\"2019-06-21\",\"effectiveDate\":\"2019-06-24\",\"rates\":[{\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"bid\":3.7194,\"ask\":3.7946},{\"currency\":\"dolar australijski\",\"code\":\"AUD\",\"bid\":2.5701,\"ask\":2.6221},{\"currency\":\"dolar kanadyjski\",\"code\":\"CAD\",\"bid\":2.8149,\"ask\":2.8717},{\"currency\":\"euro\",\"code\":\"EUR\",\"bid\":4.2128,\"ask\":4.2980},{\"currency\":\"forint (Węgry)\",\"code\":\"HUF\",\"bid\":0.012993,\"ask\":0.013255},{\"currency\":\"frank szwajcarski\",\"code\":\"CHF\",\"bid\":3.7887,\"ask\":3.8653},{\"currency\":\"funt szterling\",\"code\":\"GBP\",\"bid\":4.7150,\"ask\":4.8102},{\"currency\":\"jen (Japonia)\",\"code\":\"JPY\",\"bid\":0.034571,\"ask\":0.035269},{\"currency\":\"korona czeska\",\"code\":\"CZK\",\"bid\":0.1644,\"ask\":0.1678},{\"currency\":\"korona duńska\",\"code\":\"DKK\",\"bid\":0.5643,\"ask\":0.5757},{\"currency\":\"korona norweska\",\"code\":\"NOK\",\"bid\":0.4351,\"ask\":0.4439},{\"currency\":\"korona szwedzka\",\"code\":\"SEK\",\"bid\":0.3960,\"ask\":0.4040},{\"currency\":\"SDR (MFW)\",\"code\":\"XDR\",\"bid\":5.1697,\"ask\":5.2741}]}]";

        final String[] result = new String[2];
        Arrays.stream(nbpApi.convertJsonToObject(json)).findFirst().ifPresent(currency -> {
            result[0] = currency.getEffectiveDate();
            result[1] = currency.getRates().get(5).getCode();
        });
        final double[] result2 = new double[1];
        Arrays.stream(nbpApi.convertJsonToObject(json)).findFirst().ifPresent(currency -> result2[0] = currency.getRates().get(6).getBid());

        assertEquals("2019-06-24", result[0]);
        assertEquals("CHF", result[1]);
        assertEquals(4.715, result2[0], 0.001);
    }
}