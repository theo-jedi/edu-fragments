package com.theost.fragmentsapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagesAdapter extends FragmentStateAdapter {

    private final String[] tabs;

    public PagesAdapter(FragmentManager fragmentManager, Lifecycle lifecycle, String[] tabs) {
        super(fragmentManager, lifecycle);
        this.tabs = tabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return PageFragment.newFragment(position);
    }

    @Override
    public int getItemCount() {
        return tabs.length;
    }
}
