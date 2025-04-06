package com.salazarisaiahnoel.skillswap.fragments;

import static com.salazarisaiahnoel.skillswap.MainActivity.DATABASE_NAME;
import static com.salazarisaiahnoel.skillswap.MainActivity.LIKES_TABLE_NAME;
import static com.salazarisaiahnoel.skillswap.others.GlobalThings.easySQL;
import static com.salazarisaiahnoel.skillswap.others.GlobalThings.skill_items;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.saiaaaaaaa.cod.EasySQL;
import com.salazarisaiahnoel.skillswap.R;
import com.salazarisaiahnoel.skillswap.adapters.SkillCategoryListAdapter;
import com.salazarisaiahnoel.skillswap.adapters.SkillCategoryListLikesAdapter;
import com.salazarisaiahnoel.skillswap.others.SkillItem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TabFavoritesFragment extends Fragment implements SkillCategoryListLikesAdapter.SkillCategoryItemOnClick{

    static List<SkillItem> items = new ArrayList<>();
    static Context context;
    static FragmentActivity activity;
    static RecyclerView rv;
    static SkillCategoryListLikesAdapter adapter;

    public TabFavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.skill_faves_rv);
        rv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        context = requireContext();
        activity = requireActivity();

        refreshData();
    }

    public static void refreshData(){
        items.clear();
        for (int a = 0; a < easySQL.getTableValues(DATABASE_NAME, LIKES_TABLE_NAME).size(); a++){
            String title = easySQL.getTableValues(DATABASE_NAME, LIKES_TABLE_NAME).get(a).split(":")[1].substring(1, easySQL.getTableValues(DATABASE_NAME, LIKES_TABLE_NAME).get(a).split(":")[1].length() - 1);
            for (SkillItem item : skill_items){
                if (item.getTitle().equals(title)){
                    items.add(item);
                }
            }
        }
        items.sort(new Comparator<SkillItem>() {
            @Override
            public int compare(SkillItem skillItem, SkillItem t1) {
                return skillItem.getTitle().compareTo(t1.getTitle());
            }
        });
        adapter = new SkillCategoryListLikesAdapter(context, items, new SkillCategoryListLikesAdapter.SkillCategoryItemOnClick() {
            @Override
            public void click(int position) {
                SkillPageFragment spf = new SkillPageFragment(skill_items.get(skill_items.indexOf(items.get(position))), true);
                spf.setEnterTransition(new Slide(Gravity.END));
                spf.setExitTransition(new Slide(Gravity.END));
                activity.getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, spf).commit();
            }
        });
        rv.setAdapter(adapter);
    }

    @Override
    public void click(int position) {
        SkillPageFragment spf = new SkillPageFragment(skill_items.get(skill_items.indexOf(items.get(position))), true);
        spf.setEnterTransition(new Slide(Gravity.END));
        spf.setExitTransition(new Slide(Gravity.END));
        requireActivity().getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, spf).commit();
    }
}