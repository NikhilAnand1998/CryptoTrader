package com.test.cryptotrader;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkUtils {
    private static final OkHttpClient mHTTPClient = new OkHttpClient();



    public static String doHttpGet(String url) throws IOException{
        Request req = new Request.Builder().url(url).build();
        Response res = mHTTPClient.newCall(req).execute();
        try{
            return res.body().string();
        }finally {
            res.close();
        }
    }
}
