package com.salazarisaiahnoel.skillswap.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.salazarisaiahnoel.skillswap.R;
import com.salazarisaiahnoel.skillswap.others.SkillItem;

import java.util.List;

public class SkillListAdapter extends RecyclerView.Adapter<SkillListAdapter.SkillListHolder>{

    Context context;
    List<SkillItem> items;
    SkillItemOnClick click;

    public SkillListAdapter(Context context, List<SkillItem> items, SkillItemOnClick click){
        this.context = context;
        this.items = items;
        this.click = click;
    }

    @NonNull
    @Override
    public SkillListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_item_layout, parent, false);
        return new SkillListHolder(view, click);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillListHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.instructor.setText(items.get(position).getInstructor());
        if (holder.getAdapterPosition() == 0){
            int dp24 = (int) (24 * context.getResources().getDisplayMetrics().density);
            int dp16 = (int) (16 * context.getResources().getDisplayMetrics().density);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.skill_category_click.getLayoutParams();
            params.setMargins(dp24, dp16, dp24, dp16);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface SkillItemOnClick {
        void click(int position);
    }

    class SkillListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ConstraintLayout skill_category_click;
        TextView title, instructor;
        SkillItemOnClick click;

        public SkillListHolder(@NonNull View itemView, SkillItemOnClick click) {
            super(itemView);

            skill_category_click = itemView.findViewById(R.id.skill_item_click);
            title = itemView.findViewById(R.id.skill_item_title);
            instructor = itemView.findViewById(R.id.skill_item_instructor);
            title.setSelected(true);
            instructor.setSelected(true);
            this.click = click;

            itemView.setOnClickListener(this);
            skill_category_click.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            click.click(getAdapterPosition());
        }
    }
}
