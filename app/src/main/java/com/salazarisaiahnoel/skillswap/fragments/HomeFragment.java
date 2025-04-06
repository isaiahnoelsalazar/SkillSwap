package com.salazarisaiahnoel.skillswap.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.salazarisaiahnoel.skillswap.R;

public class HomeFragment extends Fragment {

    public static BottomNavigationView bnv;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeFrame, new TabHomeFragment()).commit();

        bnv = view.findViewById(R.id.bnv);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.tab_home){
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeFrame, new TabHomeFragment()).commit();
                    return true;
                }
                if (item.getItemId() == R.id.tab_favorites){
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeFrame, new TabFavoritesFragment()).commit();
                    return true;
                }
                if (item.getItemId() == R.id.tab_skills){
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeFrame, new TabSkillsFragment()).commit();
                    return true;
                }
                return false;
            }
        });
    }
}