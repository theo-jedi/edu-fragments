package com.theost.fragmentsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.theost.fragmentsapp.databinding.FragmentPagesBinding;

public class PagesFragment extends Fragment {

    private FragmentPagesBinding binding;

    public static Fragment newFragment() {
        Fragment fragment = new PagesFragment();
        Bundle arguments = new Bundle();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPagesBinding.inflate(getLayoutInflater());

        String[] tabNames = new String[]{"News", "Special", "Account"};

        ViewPager2 pages = binding.getRoot().findViewById(R.id.pages);
        TabLayout tabs = binding.getRoot().findViewById(R.id.tabs);

        PagesAdapter adapter = new PagesAdapter(getChildFragmentManager(), getLifecycle(), tabNames);
        pages.setAdapter(adapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabs, pages, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabNames[position]);
            }
        });

        tabLayoutMediator.attach();

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

}
