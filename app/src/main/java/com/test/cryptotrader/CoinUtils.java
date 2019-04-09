package com.test.cryptotrader;

import android.net.Uri;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class CoinUtils {
//    private final static String apiKey = "0e98871978e187a32f8075b16e73f44dccf6a22a10a42ac9069aab0a361dbbb9";
//    private final static String baseUrl = "https://min-api.cryptocompare.com/data/price?fsym=";
//    private final static String COIN_NAME ="TEST_COIN_NAME";
//    private final static String COMPARE_NAME = "USD";
//    private final static String API_PAIR = "blah";
//    private final static String FILLER_DATA = "Filler";

    public static class CoinModel{
        public String id;
        public String name;
        public String symbol;
        public String price_usd;
        public String percent_change_1h;
        public String percent_change_24h;
        public String percent_change_7d;

        public CoinModel(String id, String name, String symbol, String price_usd,String percent_change_1h,String percent_change_24h, String percent_change_7d){
            this.id = id;
            this.name = name;
            this.symbol = symbol;
            this.price_usd = price_usd;
            this.percent_change_1h = percent_change_1h;
            this.percent_change_24h = percent_change_24h;
            this.percent_change_7d = percent_change_7d;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getPrice_usd() {
            return price_usd;
        }

        public void setPrice_usd(String price_usd) {
            this.price_usd = price_usd;
        }

        public String getPercent_change_1h() {
            return percent_change_1h;
        }

        public void setPercent_change_1h(String percent_change_1h) {
            this.percent_change_1h = percent_change_1h;
        }

        public String getPercent_change_24h() {
            return percent_change_24h;
        }

        public void setPercent_change_24h(String percent_change_24h) {
            this.percent_change_24h = percent_change_24h;
        }

        public String getPercent_change_7d() {
            return percent_change_7d;
        }

        public void setPercent_change_7d(String percent_change_7d) {
            this.percent_change_7d = percent_change_7d;
        }
    }



    public static String buildUrl(){
        return ("https://api.coinmarketcap.com/v1/ticker/?limit=10");

    }

    public static ArrayList<CoinModel> parseApiCallResults(String json){
        Gson gson = new Gson();

//        ArrayList<CoinModel> results = gson.fromJson(json,CoinModel.class);
        ArrayList<CoinModel> results = gson.fromJson(json, new TypeToken<ArrayList<CoinModel>>(){}.getType());
        if(results != null ){
            return results;
        }
        else{
            return null;
        }
    }
}

