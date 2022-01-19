package com.theost.fragmentsapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.theost.fragmentsapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String EXAMPLE_FRAGMENT_TAG = "example_fragment";
    private static final String PAGES_FRAGMENT_TAG = "example_fragment";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addButton.setOnClickListener(v -> actionFragment(ManagerAction.ACTION_ADD));
        binding.replaceButton.setOnClickListener(v -> actionFragment(ManagerAction.ACTION_REPLACE));
        binding.removeButton.setOnClickListener(v -> actionFragment(ManagerAction.ACTION_REMOVE));

        binding.showPages.setOnClickListener(v -> showPagesFragment());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        binding.addButton.setVisibility(View.VISIBLE);
        binding.replaceButton.setVisibility(View.VISIBLE);
        binding.removeButton.setVisibility(View.VISIBLE);
        binding.backstackCheckbox.setVisibility(View.VISIBLE);
        binding.showPages.setVisibility(View.VISIBLE);
    }

    private void actionFragment(ManagerAction action) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(
                R.anim.slide_from_right,
                R.anim.slide_to_left,
                R.anim.slide_to_right,
                R.anim.slide_from_left
        );

        if (action == ManagerAction.ACTION_ADD) {
            transaction.add(R.id.fragmentContainer, ExampleFragment.newFragment(), EXAMPLE_FRAGMENT_TAG);
        } else if (action == ManagerAction.ACTION_REPLACE) {
            transaction.replace(R.id.fragmentContainer, ExampleFragment.newFragment(), EXAMPLE_FRAGMENT_TAG);
        } else if (action == ManagerAction.ACTION_REMOVE) {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(EXAMPLE_FRAGMENT_TAG);
            if (fragment != null) transaction.remove(fragment);
        }

        if (binding.backstackCheckbox.isChecked()) transaction.addToBackStack(null);

        transaction.commit();
    }

    private void showPagesFragment() {
        binding.addButton.setVisibility(View.INVISIBLE);
        binding.replaceButton.setVisibility(View.INVISIBLE);
        binding.removeButton.setVisibility(View.INVISIBLE);
        binding.backstackCheckbox.setVisibility(View.INVISIBLE);
        binding.showPages.setVisibility(View.INVISIBLE);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, PagesFragment.newFragment(), PAGES_FRAGMENT_TAG)
                .addToBackStack(null)
                .commit();
    }

}