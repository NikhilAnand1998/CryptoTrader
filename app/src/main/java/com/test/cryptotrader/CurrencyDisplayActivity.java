package com.test.cryptotrader;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class CurrencyDisplayActivity extends AppCompatActivity {

    private static final String TAG = CurrencyDisplayActivity.class.getSimpleName();
        private RecyclerView mCoinListRV;
        public static CurrencyAdapter mCurrencyAdapter;
        private TextView mLoadingErrorTV;
        private ProgressBar mLoadingPB;
        private ArrayList<CoinUtils.CoinModel> model = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_display);
        model = null;
        mCoinListRV = findViewById(R.id.rv_currency_list);
        mCurrencyAdapter = new CurrencyAdapter();
        mLoadingErrorTV = findViewById(R.id.tv_loading_error);
        mLoadingPB = findViewById(R.id.pb_loading);

        mCoinListRV.setLayoutManager(new LinearLayoutManager(this));
        mCoinListRV.setHasFixedSize(true);

        mCurrencyAdapter = new CurrencyAdapter();
        mCoinListRV.setAdapter(mCurrencyAdapter);
        mCoinListRV.setItemAnimator(new DefaultItemAnimator());
        mCoinListRV.addItemDecoration(new DividerItemDecoration(mCoinListRV.getContext(),DividerItemDecoration.VERTICAL));
        DefaultCoins();

        ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                ((CurrencyAdapter.CurrencyViewHolder)viewHolder).removeCoin();
            }
        };
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
    itemTouchHelper.attachToRecyclerView(mCoinListRV);

    }

    private void DefaultCoins(){
        String url = CoinUtils.buildUrl();
//        Log.d(TAG,"querying price for: " + coin_code + " at URL: "+ url);
        new APICoinTask().execute(url);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.crypto_display_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.new_coin_button:
                addCoinScreen();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addCoinScreen(){
        Intent intent = new Intent(this,AddCoin.class );
        startActivity(intent);
    }

    public  class APICoinTask extends AsyncTask<String, Void, String>{

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
