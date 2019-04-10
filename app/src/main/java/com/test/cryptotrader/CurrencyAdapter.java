package com.test.cryptotrader;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {


    public static ArrayList<CoinUtils.CoinModel> items;

    public CurrencyAdapter(){
        items = new ArrayList<>();
    }
    public void updateCoin(ArrayList<CoinUtils.CoinModel> model){
        this.items = model;
        notifyDataSetChanged();
    }

    public void addCoin(CoinUtils.CoinModel model){
        items.add(model);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        return  items.size();
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
        CoinUtils.CoinModel item = items.get(i);
        currencyViewHolder.mName.setText(item.getName());
        currencyViewHolder.mSymbol.setText(item.getSymbol());
        currencyViewHolder.mOneHour.setText(item.getPercent_change_1h() + "%");
        currencyViewHolder.m24Hour.setText(item.getPercent_change_24h() + "%");
        currencyViewHolder.mSevenDays.setText(item.getPercent_change_7d() + "%");

        double price = Double.parseDouble(item.getPrice_usd());
        price = Math.round(price * 100);
        price = price/100;
        currencyViewHolder.mPrice.setText("$" + Double.toString(price));

        if(item.percent_change_1h.contains("-")){
            currencyViewHolder.mOneHour.setTextColor(Color.parseColor("#FF0000"));
        }else{
            currencyViewHolder.mOneHour.setTextColor(Color.parseColor("#0f9d58"));
        }

        if(item.percent_change_24h.contains("-")){
            currencyViewHolder.m24Hour.setTextColor(Color.parseColor("#FF0000"));
        }else{
            currencyViewHolder.m24Hour.setTextColor(Color.parseColor("#0f9d58"));
        }
        if(item.percent_change_7d.contains("-")){
            currencyViewHolder.mSevenDays.setTextColor(Color.parseColor("#FF0000"));
        }else{
            currencyViewHolder.mSevenDays.setTextColor(Color.parseColor("#0f9d58"));
        }

        Picasso.with(currencyViewHolder.itemView.getContext()).load(new StringBuilder("https://res.cloudinary.com/dxi90ksom/image/upload/")
        .append(item.getSymbol().toLowerCase()).append(".png").toString()).into(currencyViewHolder.mImage);

        currencyViewHolder.itemView.setBackgroundColor(Color.parseColor("#E4F6F8"));
    }
    class CurrencyViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImage;
        public TextView  mSymbol,mName,mPrice,mOneHour,m24Hour,mSevenDays;

        public CurrencyViewHolder(View itemView){
            super(itemView);
            mImage = itemView.findViewById(R.id.coin_image);
            mSymbol = itemView.findViewById(R.id.tv_coin_text);
            mName = itemView.findViewById(R.id.tv_coin_name);
            mPrice = itemView.findViewById(R.id.tv_coin_price);
            mOneHour = itemView.findViewById(R.id.tv_one_hour);
            m24Hour = itemView.findViewById(R.id.tv_24_hour);
            mSevenDays = itemView.findViewById(R.id.tv_7_days);
        }


        public void removeCoin(){
            int position = getAdapterPosition();
            items.remove(position);
            notifyItemRemoved(position);
        }

    }
}
