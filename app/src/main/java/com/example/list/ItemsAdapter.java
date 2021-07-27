package com.example.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private List<Item> itemList = new ArrayList<>();

    public void setData(List<Item> items) {
        itemList.clear();
        itemList.addAll(items);

        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ItemViewHolder(layoutInflater.inflate(R.layout.cell_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ItemsAdapter.ItemViewHolder holder, int position) {
        holder.bind(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView valueTextView;

        public ItemViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.cellItemTitleView);
            valueTextView = itemView.findViewById(R.id.cellItemValueView);
        }

        public void bind(Item item) {
            titleTextView.setText(item.getTitle());
            valueTextView.setText(item.getValue());
        }
    }
}
