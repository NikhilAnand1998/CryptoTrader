package com.test.cryptotrader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class AddCoin extends AppCompatActivity {

    private EditText mCoinSearch;
    private Toast mToast;
    private TextView mLoadingErrorTV;
    private ProgressBar mLoadingPB;
    private CurrencyAdapter mCurrencyAdapter;
    private static final String TAG = AddCoin.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_coin);

        mCurrencyAdapter = CurrencyDisplayActivity.mCurrencyAdapter;
        mLoadingErrorTV = findViewById(R.id.tv_loading_error_add_coin);
        mLoadingPB = findViewById(R.id.pb_loading_add_coin);
        mCoinSearch = findViewById(R.id.et_search_box);
        Button mSearchButton = findViewById(R.id.btn_search);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = mCoinSearch.getText().toString();
                search = search.toLowerCase();
             if(!TextUtils.isEmpty(search)){
                 addCoinSearch(search);
                 mCoinSearch.setText("");
             }
            }
        });

    }

    private void addCoinSearch(String newID){
        String url = CoinUtils.addCoinUrl(newID);
        Log.d(TAG,"querying data for coin at URL: " + url);
        new APISingleCoinTask().execute(url);
           }

    class APISingleCoinTask extends AsyncTask<String, Void, String>{
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
            if(s !=null){
                mLoadingErrorTV.setVisibility(View.INVISIBLE);
               CoinUtils.CoinModel model = CoinUtils.parseSingleApiCallResults(s);
                mCurrencyAdapter.addCoin(model);
            }else{
                mLoadingErrorTV.setVisibility(View.VISIBLE);
            }
            mLoadingPB.setVisibility(View.INVISIBLE);

        }
    }

}
