package com.salazarisaiahnoel.skillswap.fragments;

import static com.salazarisaiahnoel.skillswap.others.GlobalThings.skill_items;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salazarisaiahnoel.skillswap.R;
import com.salazarisaiahnoel.skillswap.adapters.SkillListAdapter;
import com.salazarisaiahnoel.skillswap.others.SkillItem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TabSkillsFragment extends Fragment implements SkillListAdapter.SkillItemOnClick {

    List<SkillItem> temp = new ArrayList<>();
    String searchParams;

    public TabSkillsFragment() {
        // Required empty public constructor
    }

    public TabSkillsFragment(String searchParams) {
        // Required empty public constructor
        this.searchParams = searchParams;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_skills, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rv = view.findViewById(R.id.skillrv);
        rv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        temp.addAll(skill_items);

        temp.sort(new Comparator<SkillItem>() {
            @Override
            public int compare(SkillItem skillItem, SkillItem t1) {
                return skillItem.getTitle().compareTo(t1.getTitle());
            }
        });

        if (!TextUtils.isEmpty(searchParams)){
            temp.clear();
            for (SkillItem item : skill_items){
                if (item.getTitle().toLowerCase().contains(searchParams)){
                    temp.add(item);
                }
            }
        }

        SkillListAdapter adapter = new SkillListAdapter(requireContext(), temp, this);
        rv.setAdapter(adapter);
    }

    @Override
    public void click(int position) {
        SkillPageFragment spf = new SkillPageFragment(temp.get(position), true);
        spf.setEnterTransition(new Slide(Gravity.END));
        spf.setExitTransition(new Slide(Gravity.END));
        requireActivity().getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, spf).commit();
    }
}