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
import com.salazarisaiahnoel.skillswap.others.MessageItem;
import com.salazarisaiahnoel.skillswap.others.SkillItem;

import java.util.Collections;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder>{

    Context context;
    List<MessageItem> items;

    public MessageAdapter(Context context, List<MessageItem> items){
        this.context = context;
        this.items = items;
        Collections.reverse(items);
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        holder.title.setText(items.get(position).getMessageContent());
        if (holder.getAdapterPosition() == 0){
            int dp24 = (int) (24 * context.getResources().getDisplayMetrics().density);
            int dp8 = (int) (8 * context.getResources().getDisplayMetrics().density);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.messagecontentcontainer.getLayoutParams();
            params.setMargins(dp24, dp8, dp24, dp8);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MessageHolder extends RecyclerView.ViewHolder {

        TextView title;
        ConstraintLayout messagecontentcontainer;

        public MessageHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.message_content);
            messagecontentcontainer = itemView.findViewById(R.id.messagecontentcontainer);
        }
    }
}
