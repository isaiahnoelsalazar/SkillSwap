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

import com.salazarisaiahnoel.skillswap.R;
import com.salazarisaiahnoel.skillswap.adapters.SkillCategoryAdapter;
import com.salazarisaiahnoel.skillswap.others.SkillItem;

import java.util.ArrayList;
import java.util.List;

public class TabHomeFragment extends Fragment implements SkillCategoryAdapter.SkillCategoryOnClick{

    List<String> categories = new ArrayList<>();

    public TabHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rv = view.findViewById(R.id.homerv);
        rv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        List<SkillItem> items = new ArrayList<>();
        for (SkillItem item : skill_items){
            if (!categories.contains(item.getCategory())){
                items.add(item);
                categories.add(item.getCategory());
            }
        }
        SkillCategoryAdapter adapter = new SkillCategoryAdapter(requireContext(), items, this);
        rv.setAdapter(adapter);
    }

    @Override
    public void click(int position) {
        SkillListFragment slf = new SkillListFragment(categories.get(position));
        slf.setEnterTransition(new Slide(Gravity.END));
        slf.setExitTransition(new Slide(Gravity.END));
        requireActivity().getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, slf).commit();
    }
}