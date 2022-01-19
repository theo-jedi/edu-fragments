package com.theost.fragmentsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.theost.fragmentsapp.databinding.FragmentPageBinding;

public class PageFragment extends Fragment {

    private static final String INDEX_EXTRA = "index";

    private FragmentPageBinding binding;

    public static Fragment newFragment(int index) {
        Fragment fragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(INDEX_EXTRA, index);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPageBinding.inflate(getLayoutInflater());

        int index = getArguments() != null ? getArguments().getInt(INDEX_EXTRA) : -1;
        binding.dataView.setText("Index: " + index);

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

}
