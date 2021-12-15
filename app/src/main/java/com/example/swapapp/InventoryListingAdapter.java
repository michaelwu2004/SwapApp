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
import com.example.swapapp.models.Item;
import com.example.swapapp.models.TradeListing;

import java.util.ArrayList;

public class InventoryListingAdapter implements ListAdapter {
  private ArrayList<Item> mIventoryList;
  private Context mContext;

  public InventoryListingAdapter(Context context, ArrayList<Item> inventoryList) {
    this.mContext = context;
    this.mIventoryList = inventoryList;
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
  public int getCount() { return this.mIventoryList.size(); }

  @Override
  public Object getItem(int position) { return position; }

  @Override
  public long getItemId(int position) { return position; }

  @Override
  public boolean hasStableIds() { return false; }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Item itemData = this.mIventoryList.get(position);

    if (convertView == null) {
      LayoutInflater layoutInflater = LayoutInflater.from(this.mContext);
      convertView = layoutInflater.inflate(R.layout.inventory_item_layout, null);

      convertView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) { }
      });

      TextView title = convertView.findViewById(R.id.item_name);
      title.setText(itemData.getName());

      TextView description = convertView.findViewById(R.id.item_description);
      description.setText(itemData.getDescription());

      ImageView image = convertView.findViewById(R.id.trade_listing_user_icon);
      Glide.with(this.mContext).load(itemData.getImage()).into(image);


    }

    return convertView;
  }

  @Override
  public int getItemViewType(int position) { return position; }

  @Override
  public int getViewTypeCount() { return this.mIventoryList.size(); }

  @Override
  public boolean isEmpty() { return false; }
}