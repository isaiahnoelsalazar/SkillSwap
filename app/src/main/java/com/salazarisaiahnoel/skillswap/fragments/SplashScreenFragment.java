package com.salazarisaiahnoel.skillswap.fragments;

import static com.salazarisaiahnoel.skillswap.fragments.HomeFragment.bnv;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.salazarisaiahnoel.skillswap.R;

public class SplashScreenFragment extends Fragment {

    public SplashScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation ta = new TranslateAnimation(100 * requireContext().getResources().getDisplayMetrics().density, 0, 0, 0);
                ta.setDuration(750);
                ImageView profile = view.findViewById(R.id.profile);
                profile.startAnimation(ta);
                profile.setVisibility(View.VISIBLE);
            }
        }, 1000);

        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.subFrame, new HomeFragment()).commit();

        EditText search = view.findViewById(R.id.searchbar);
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (!(requireActivity().getSupportFragmentManager().findFragmentById(R.id.homeFrame) instanceof TabSkillsFragment)){
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeFrame, new TabSkillsFragment()).commit();
                    bnv.setSelectedItemId(R.id.tab_skills);
                }
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeFrame, new TabSkillsFragment(search.getText().toString().toLowerCase())).commit();
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeFrame, new TabSkillsFragment(search.getText().toString().toLowerCase())).commit();
                    InputMethodManager inputMethodManager = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(search.getWindowToken(), 0);
                }
                return false;
            }
        });
    }
}