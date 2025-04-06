package com.salazarisaiahnoel.skillswap.adapters;

import static com.salazarisaiahnoel.skillswap.others.GlobalThings.decodeSampledBitmapFromResource;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class SkillCategoryAdapter extends RecyclerView.Adapter<SkillCategoryAdapter.SkillCategoryHolder>{

    Context context;
    List<SkillItem> items;
    SkillCategoryOnClick click;
    List<String> categories = new ArrayList<>();

    public SkillCategoryAdapter(Context context, List<SkillItem> items, SkillCategoryOnClick click){
        this.context = context;
        this.items = items;
        this.click = click;
    }

    @NonNull
    @Override
    public SkillCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_category_layout, parent, false);
        return new SkillCategoryHolder(view, click);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillCategoryHolder holder, int position) {
        if (!categories.contains(items.get(position).getCategory())){
            holder.title.setText(items.get(position).getCategory());
            holder.image.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(), items.get(position).getCategoryImage(), 512, 512));
            if (items.get(position).getCategory().equals("Fitness")){
                holder.simple_image.setRotation(90);
            }
            holder.simple_image.setImageResource(items.get(position).getSimpleImage());
            if (holder.getAdapterPosition() == 0){
                int dp24 = (int) (24 * context.getResources().getDisplayMetrics().density);
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) holder.card.getLayoutParams();
                params.setMargins(dp24, dp24, dp24, dp24);
            }
            categories.add(items.get(position).getCategory());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface SkillCategoryOnClick {
        void click(int position);
    }

    class SkillCategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ConstraintLayout skill_category_click;
        MaterialCardView card;
        TextView title;
        ImageView image, simple_image;
        SkillCategoryOnClick click;

        public SkillCategoryHolder(@NonNull View itemView, SkillCategoryOnClick click) {
            super(itemView);

            skill_category_click = itemView.findViewById(R.id.skill_category_click);
            card = itemView.findViewById(R.id.skill_category_card);
            title = itemView.findViewById(R.id.skill_category_title);
            image = itemView.findViewById(R.id.skill_category_image);
            simple_image = itemView.findViewById(R.id.skill_category_simple_image);
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
