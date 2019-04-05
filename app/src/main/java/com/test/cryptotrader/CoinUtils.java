package com.test.cryptotrader;

import android.net.Uri;
import com.google.gson.Gson;




public class CoinUtils {
    private final static String apiKey = "0e98871978e187a32f8075b16e73f44dccf6a22a10a42ac9069aab0a361dbbb9";
    private final static String baseUrl = "https://min-api.cryptocompare.com/data/price?fsym=";
    private final static String COIN_NAME ="TEST_COIN_NAME";
    private final static String COMPARE_NAME = "USD";
    private final static String API_PAIR = "blah";
    private final static String FILLER_DATA = "Filler";

    public static class CoinPrice{
        public float USD;
    }

    public static String buildUrl(String coin){
        return (baseUrl + coin + "&tsyms=USD" + "&api_key="+ apiKey);

    }

    public static CoinPrice parseApiCallResults(String json){
        Gson gson = new Gson();

        CoinPrice cp = gson.fromJson(json,CoinPrice.class);
        System.out.println("The JSON IS: "+cp);
        if(cp != null ){

            return cp;
        }
        else{
            return null;
        }
    }
}

