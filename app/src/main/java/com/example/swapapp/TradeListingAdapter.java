package com.example.swapapp;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.swapapp.models.TradeListing;

import java.util.ArrayList;

public class TradeListingAdapter implements ListAdapter {
  private ArrayList<TradeListing> mTradeListings;
  private Context mContext;

  public TradeListingAdapter(Context context, ArrayList<TradeListing> tradeListings) {
    this.mContext = context;
    this.mTradeListings = tradeListings;
  }

  @Override
  public boolean areAllItemsEnabled() { return false; }

  @Override
  public boolean isEnabled(int position) { return true; }

  @Override
  public void registerDataSetObserver(DataSetObserver observer) { }

  @Override
  public void unregisterDataSetObserver(DataSetObserver observer) { }

  @Override
  public int getCount() { return this.mTradeListings.size(); }

  @Override
  public Object getItem(int position) { return position; }

  @Override
  public long getItemId(int position) { return position; }

  @Override
  public boolean hasStableIds() { return false; }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    TradeListing tradeListingData = this.mTradeListings.get(position);

    if (convertView == null) {
      LayoutInflater layoutInflater = LayoutInflater.from(this.mContext);
      convertView = layoutInflater.inflate(R.layout.trade_listing_layout, null);

      convertView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) { }
      });

      TextView title = convertView.findViewById(R.id.trade_listing_user_name);
      title.setText(tradeListingData.getTradeInitiator().getFullName());
    }

    return convertView;
  }

  @Override
  public int getItemViewType(int position) { return position; }

  @Override
  public int getViewTypeCount() { return this.mTradeListings.size(); }

  @Override
  public boolean isEmpty() { return false; }
}