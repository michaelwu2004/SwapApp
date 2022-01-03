package com.example.swapapp;

import android.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.swapapp.models.TradeListing;
import com.example.swapapp.ui.transaction.TransactionFragment;

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
      Button trade = convertView.findViewById(R.id.trade);

      title.setText(tradeListingData.getTradeInitiator().getFullName());
      trade.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          //go to trading page
          //you can view other users items and your items
          //we pass the two user classes
          Intent i = new Intent(mContext, ExchangeActivity.class);

          i.putExtra("user1", MainActivity.user_id);
          i.putExtra("user2",  MainActivity.otherUser.getID());

          mContext.startActivity(i);





        }
      });
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