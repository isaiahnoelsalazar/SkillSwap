package com.salazarisaiahnoel.skillswap.adapters;

import static com.salazarisaiahnoel.skillswap.others.GlobalThings.decodeSampledBitmapFromResource;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.salazarisaiahnoel.skillswap.R;
import com.salazarisaiahnoel.skillswap.others.SkillItem;

import java.util.ArrayList;
import java.util.List;

public class SkillCategoryListAdapter extends RecyclerView.Adapter<SkillCategoryListAdapter.SkillCategoryListHolder>{

    Context context;
    List<SkillItem> items;
    SkillCategoryItemOnClick click;

    public SkillCategoryListAdapter(Context context, List<SkillItem> items, SkillCategoryItemOnClick click){
        this.context = context;
        this.items = items;
        this.click = click;
    }

    @NonNull
    @Override
    public SkillCategoryListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_category_item_layout, parent, false);
        return new SkillCategoryListHolder(view, click);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillCategoryListHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.image.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(), items.get(position).getImage(), 512, 512));
        if (holder.getAdapterPosition() == 0){
            int dp24 = (int) (24 * context.getResources().getDisplayMetrics().density);
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) holder.card.getLayoutParams();
            params.setMargins(dp24, dp24, dp24, dp24);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface SkillCategoryItemOnClick {
        void click(int position);
    }

    class SkillCategoryListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ConstraintLayout skill_category_click;
        MaterialCardView card;
        TextView title;
        ImageView image;
        SkillCategoryItemOnClick click;

        public SkillCategoryListHolder(@NonNull View itemView, SkillCategoryItemOnClick click) {
            super(itemView);

            skill_category_click = itemView.findViewById(R.id.skill_category_click);
            card = itemView.findViewById(R.id.skill_category_card);
            title = itemView.findViewById(R.id.skill_category_title);
            image = itemView.findViewById(R.id.skill_category_image);
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
