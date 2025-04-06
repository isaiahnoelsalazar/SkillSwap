package com.salazarisaiahnoel.skillswap.fragments;

import static com.salazarisaiahnoel.skillswap.others.GlobalThings.skill_items;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.transition.Slide;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.salazarisaiahnoel.skillswap.R;

public class InstructorDetailFragment extends Fragment {

    String instructorName;

    public InstructorDetailFragment(String instructorName) {
        // Required empty public constructor
        this.instructorName = instructorName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instructor_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().getWindow().setStatusBarColor(requireContext().getColor(R.color.white));
        requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        ImageView backarrow = view.findViewById(R.id.back_arrow);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getWindow().setStatusBarColor(requireContext().getColor(R.color.blue));
                int flags = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                requireActivity().getWindow().getDecorView().setSystemUiVisibility(flags);
                requireActivity().getSupportFragmentManager().beginTransaction().remove(requireActivity().getSupportFragmentManager().findFragmentById(R.id.mainFrame)).commit();
            }
        });

        ConstraintLayout message_container = view.findViewById(R.id.message_container);
        ImageView message_button = view.findViewById(R.id.message_button);
        message_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageFragment mf = new MessageFragment(instructorName);
                mf.setEnterTransition(new Slide(Gravity.END));
                mf.setExitTransition(new Slide(Gravity.END));
                requireActivity().getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, mf).commit();
            }
        });
        message_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageFragment mf = new MessageFragment(instructorName);
                mf.setEnterTransition(new Slide(Gravity.END));
                mf.setExitTransition(new Slide(Gravity.END));
                requireActivity().getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, mf).commit();
            }
        });

        TextView instructor_name = view.findViewById(R.id.instructor_name);
        instructor_name.setText(instructorName);
    }
}