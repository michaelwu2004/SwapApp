package com.example.swapapp.ui.transaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.swapapp.databinding.FragmentFindBinding;

public class TransactionFragment extends Fragment {
  private TransactionViewModel mFindViewModel;
  private FragmentFindBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    this.mFindViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
    binding = FragmentFindBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    return root;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
