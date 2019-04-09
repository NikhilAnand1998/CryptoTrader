package com.test.cryptotrader;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkUtils {
    private static final OkHttpClient mHTTPClient = new OkHttpClient();



    public static String doHttpGet(String url) throws IOException{
        Headers headerbuild = Headers.of("X-CMC_PRO_API_KEY", "bd1e6a4b-81db-4e5a-9e31-6b4562d20f30");
        Request req = new Request.Builder().url(url).headers(headerbuild).build();
        Response res = mHTTPClient.newCall(req).execute();
        try{
            return res.body().string();
        }finally {
            res.close();
        }
    }
}
