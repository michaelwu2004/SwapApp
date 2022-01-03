package com.example.swapapp.ui.find;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.swapapp.MainActivity;
import com.example.swapapp.TradeListingAdapter;
import com.example.swapapp.databinding.FragmentFindBinding;
import com.example.swapapp.models.TradeListing;
import com.example.swapapp.models.User;

import java.util.ArrayList;

public class FindFragment extends Fragment {
  private FindViewModel mFindViewModel;
  private FragmentFindBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    this.mFindViewModel = new ViewModelProvider(this).get(FindViewModel.class);
    binding = FragmentFindBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    ListView listingListView = binding.tradeListingList;
    ArrayList<TradeListing> tempTradeListings = new ArrayList<>();
    tempTradeListings.add(new TradeListing(MainActivity.otherUser));


    TradeListingAdapter tradeListingAdapter = new TradeListingAdapter(getActivity(), tempTradeListings);
    listingListView.setAdapter(tradeListingAdapter);

//    final TextView textView = binding.textFind;
//    this.mFindViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//      @Override
//      public void onChanged(String s) {
//        textView.setText(s);
//      }
//    });

    return root;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
