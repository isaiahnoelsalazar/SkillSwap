package com.salazarisaiahnoel.skillswap.fragments;

import static com.salazarisaiahnoel.skillswap.MainActivity.DATABASE_NAME;
import static com.salazarisaiahnoel.skillswap.MainActivity.LIKES_TABLE_NAME;
import static com.salazarisaiahnoel.skillswap.fragments.TabFavoritesFragment.refreshData;
import static com.salazarisaiahnoel.skillswap.others.GlobalThings.decodeSampledBitmapFromResource;
import static com.salazarisaiahnoel.skillswap.others.GlobalThings.skill_items;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.transition.Slide;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.saiaaaaaaa.cod.EasySQL;
import com.salazarisaiahnoel.skillswap.R;
import com.salazarisaiahnoel.skillswap.others.SkillItem;

public class SkillPageFragment extends Fragment {

    SkillItem item;
    public static boolean fromAllSkills;

    public SkillPageFragment(SkillItem item) {
        // Required empty public constructor
        this.item = item;
        fromAllSkills = false;
    }

    public SkillPageFragment(SkillItem item, boolean fromAllSkills) {
        // Required empty public constructor
        this.item = item;
        this.fromAllSkills = fromAllSkills;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().getWindow().setStatusBarColor(requireContext().getColor(R.color.blue));
        int flags = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        requireActivity().getWindow().getDecorView().setSystemUiVisibility(flags);

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(requireView().getWindowToken(), 0);
        } catch (Exception ignored){
        }

        EasySQL easySQL = new EasySQL(requireContext());

        ImageView backarrow = view.findViewById(R.id.back_arrow);
        ImageView like_button = view.findViewById(R.id.like_button);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fromAllSkills){
                    requireActivity().getWindow().setStatusBarColor(requireContext().getColor(R.color.white));
                    requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }
                try {
                    refreshData();
                } catch (Exception ignored){
                }
                requireActivity().getSupportFragmentManager().beginTransaction().remove(requireActivity().getSupportFragmentManager().findFragmentById(R.id.mainFrame)).commit();
            }
        });
        for (String a : easySQL.getTableValues(DATABASE_NAME, LIKES_TABLE_NAME)){
            System.out.println(a);
        }
        if (easySQL.getTableValues(DATABASE_NAME, LIKES_TABLE_NAME).contains("title:'" + item.getTitle() + "'")){
            like_button.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.active_like_bg));
        }
        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!easySQL.getTableValues(DATABASE_NAME, LIKES_TABLE_NAME).contains("title:'" + item.getTitle() + "'")){
                    easySQL.insertToTable(DATABASE_NAME, LIKES_TABLE_NAME, new String[]{"title:" + item.getTitle()});
                    like_button.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.active_like_bg));
                } else {
                    easySQL.deleteFromTable(DATABASE_NAME, LIKES_TABLE_NAME, "title:" + item.getTitle());
                    like_button.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.back_arrow_bg));
                }
            }
        });

        TextView title = view.findViewById(R.id.skill_page_title);
        TextView level = view.findViewById(R.id.skill_page_level);
        TextView description = view.findViewById(R.id.skill_page_description);
        TextView instructor = view.findViewById(R.id.skill_page_instructor);
        ImageView image = view.findViewById(R.id.skill_page_image);

        ConstraintLayout instructor_detail = view.findViewById(R.id.instructor_detail);
        instructor_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InstructorDetailFragment idf = new InstructorDetailFragment(instructor.getText().toString());
                idf.setEnterTransition(new Slide(Gravity.END));
                idf.setExitTransition(new Slide(Gravity.END));
                requireActivity().getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, idf).commit();
            }
        });

        title.setText(item.getTitle());
        level.setText(item.getLevel());
        if (item.getLevel().equals("Intermediate")){
            level.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.intermediatelevel_bg));
        }
        if (item.getLevel().equals("Advanced")){
            level.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.advancedlevel_bg));
        }
        description.setText(item.getDescription());
        instructor.setText(item.getInstructor());
        image.setImageBitmap(decodeSampledBitmapFromResource(requireContext().getResources(), item.getImage(), 512, 512));

        ImageView message_button = view.findViewById(R.id.message_button);
        message_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageFragment mf = new MessageFragment(instructor.getText().toString());
                mf.setEnterTransition(new Slide(Gravity.END));
                mf.setExitTransition(new Slide(Gravity.END));
                requireActivity().getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, mf).commit();
            }
        });
    }
}