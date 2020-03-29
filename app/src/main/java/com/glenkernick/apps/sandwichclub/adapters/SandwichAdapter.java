package com.glenkernick.apps.sandwichclub.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.glenkernick.apps.sandwichclub.DetailActivity;
import com.glenkernick.apps.sandwichclub.R;
import com.glenkernick.apps.sandwichclub.model.Sandwich;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.ViewHolder> {

    private final List<Sandwich> mSandwichList;

    public SandwichAdapter(List<Sandwich> sandwichList) {
        mSandwichList = sandwichList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sandwich sandwich = mSandwichList.get(position);
        holder.sandwichName.setText(sandwich.getMainName());

        Picasso.get()
                .load(sandwich.getImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.sandwichImage);
    }

    @Override
    public int getItemCount() {
        return mSandwichList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sandwichName;
        ImageView sandwichImage;

        ViewHolder(View itemView) {
            super(itemView);
            sandwichName = itemView.findViewById(R.id.sandwichName);
            sandwichImage = itemView.findViewById(R.id.sandwichImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    launchDetailActivity(view, getAdapterPosition());
                }
            });
        }

        private void launchDetailActivity(View view, int position) {
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_POSITION, position);
            view.getContext().startActivity(intent);
        }
    }
}