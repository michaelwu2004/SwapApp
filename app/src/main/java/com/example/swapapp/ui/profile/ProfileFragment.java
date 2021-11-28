package com.example.swapapp.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.swapapp.AddItem;
import com.example.swapapp.InventoryListingAdapter;
import com.example.swapapp.MainActivity;
import com.example.swapapp.databinding.FragmentProfileBinding;
import com.example.swapapp.models.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
  private ProfileViewModel mProfileViewModel;
  private FragmentProfileBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    this.mProfileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    binding = FragmentProfileBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    ListView inventoryListView = binding.profileInventoryList;
    Button fab = binding.addItemBtn;

    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(getActivity(), AddItem.class);
        startActivity(i);
      }
    });

    this.mProfileViewModel.items.observe(getViewLifecycleOwner(), new Observer<ArrayList<Item>>() {
      @Override
      public void onChanged(ArrayList<Item> items) {
        if (items.size() > 0) {
          InventoryListingAdapter inventoryListingAdapter = new InventoryListingAdapter(getActivity(), items);
          inventoryListView.setAdapter(inventoryListingAdapter);
        }
      }
    });

    return root;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
