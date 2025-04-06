package com.salazarisaiahnoel.skillswap.fragments;

import static com.salazarisaiahnoel.skillswap.others.GlobalThings.skill_items;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.salazarisaiahnoel.skillswap.R;
import com.salazarisaiahnoel.skillswap.adapters.SkillCategoryAdapter;
import com.salazarisaiahnoel.skillswap.adapters.SkillCategoryListAdapter;
import com.salazarisaiahnoel.skillswap.others.SkillItem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SkillListFragment extends Fragment implements SkillCategoryListAdapter.SkillCategoryItemOnClick{

    String title;
    List<SkillItem> items = new ArrayList<>();

    public SkillListFragment(String title) {
        // Required empty public constructor
        this.title = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill_list, container, false);
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

        TextView skill_list_title = view.findViewById(R.id.skill_list_title);
        skill_list_title.setText(title);

        RecyclerView rv = view.findViewById(R.id.skill_list_rv);
        rv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        for (SkillItem item : skill_items){
            if (item.getCategory().equals(title)){
                items.add(item);
            }
        }
        items.sort(new Comparator<SkillItem>() {
            @Override
            public int compare(SkillItem skillItem, SkillItem t1) {
                return skillItem.getTitle().compareTo(t1.getTitle());
            }
        });
        SkillCategoryListAdapter adapter = new SkillCategoryListAdapter(requireContext(), items, this);
        rv.setAdapter(adapter);
    }

    @Override
    public void click(int position) {
        SkillPageFragment spf = new SkillPageFragment(skill_items.get(skill_items.indexOf(items.get(position))));
        spf.setEnterTransition(new Slide(Gravity.END));
        spf.setExitTransition(new Slide(Gravity.END));
        requireActivity().getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, spf).commit();
    }
}