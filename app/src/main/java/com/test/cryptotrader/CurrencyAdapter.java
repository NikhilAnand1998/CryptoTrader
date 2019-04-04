package com.test.cryptotrader;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {

    private ArrayList<String> mCurrencyList;

    public CurrencyAdapter(){
        mCurrencyList = new ArrayList<String>(Arrays.asList("Bitcoin","Ethereum","XRP","Bitcoin Cash","Litecoin","EOS","Binance Coin","Stellar","Cardano","Tether","TRON","Bitcoin SV","Drash","Monero","IOTA","NEO","Ontology","Maker","Tezos","NEM"));
    }
    public void addCoin(String coin){
        mCurrencyList.add(coin);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        return  mCurrencyList.size();
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i ) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.currency_list_coin, viewGroup, false);
        return new CurrencyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder currencyViewHolder, int i){
        String coin = mCurrencyList.get(i);
        currencyViewHolder.bind(coin);
    }
    class CurrencyViewHolder extends RecyclerView.ViewHolder{
        private TextView mCoinTV;

        public CurrencyViewHolder(View itemView){
            super(itemView);
            mCoinTV = itemView.findViewById(R.id.tv_coin_text);
        }

        public void bind(String coin){
            mCoinTV.setText(coin);
        }
    }
}
