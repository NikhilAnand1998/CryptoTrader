package com.test.cryptotrader;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class CurrencyDisplayActivity extends AppCompatActivity {

    private static final String TAG = CurrencyDisplayActivity.class.getSimpleName();
        private RecyclerView mCoinListRV;
        private CurrencyAdapter mCurrencyAdapter;
        private TextView mLoadingErrorTV;
        private ProgressBar mLoadingPB;
        private ArrayList<CoinUtils.CoinModel> model = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_display);

        mCoinListRV = findViewById(R.id.rv_currency_list);
        mCurrencyAdapter = new CurrencyAdapter();
        mLoadingErrorTV = findViewById(R.id.tv_loading_error);
        mLoadingPB = findViewById(R.id.pb_loading);

        mCoinListRV.setLayoutManager(new LinearLayoutManager(this));
        mCoinListRV.setHasFixedSize(true);

        mCurrencyAdapter = new CurrencyAdapter();
        mCoinListRV.setAdapter(mCurrencyAdapter);

        mCoinListRV.addItemDecoration(new DividerItemDecoration(mCoinListRV.getContext(),DividerItemDecoration.VERTICAL));
        DefaultCoins();


    }

    private void DefaultCoins(){
        String url = CoinUtils.buildUrl();
//        Log.d(TAG,"querying price for: " + coin_code + " at URL: "+ url);
        new APICoinTask().execute(url);
    }
    class APICoinTask extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mLoadingPB.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String...urls){
            String url = urls[0];
            String results = null;
            try{
                results = NetworkUtils.doHttpGet(url);
            }catch (IOException e){
                e.printStackTrace();
            }
            return results;
        }
        @Override
        protected void onPostExecute(String s){

            if(s != null){
                mLoadingErrorTV.setVisibility(View.INVISIBLE);
                mCoinListRV.setVisibility(View.VISIBLE);

                ArrayList<CoinUtils.CoinModel> item = CoinUtils.parseApiCallResults(s);
                mCurrencyAdapter.updateCoin(item);
            }else{
                mLoadingErrorTV.setVisibility(View.VISIBLE);
                mCoinListRV.setVisibility(View.INVISIBLE);
            }
            mLoadingPB.setVisibility(View.INVISIBLE);
        }
    }
}
