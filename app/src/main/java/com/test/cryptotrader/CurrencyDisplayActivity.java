package com.test.cryptotrader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class CurrencyDisplayActivity extends AppCompatActivity {

        private RecyclerView mCoinListRV;
        private CurrencyAdapter mCurrencyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_display);

        mCoinListRV = findViewById(R.id.rv_currency_list);
        mCurrencyAdapter = new CurrencyAdapter();

        mCoinListRV.setLayoutManager(new LinearLayoutManager(this));
        mCoinListRV.setHasFixedSize(true);
        mCoinListRV.setAdapter(mCurrencyAdapter);


    }
}
