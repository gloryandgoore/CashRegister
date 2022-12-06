package com.example.cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class HistoryRecycleAdapter extends RecyclerView.Adapter<HistoryRecycleAdapter.myViewHolder> {

    interface ItemListener {
        void onItemClicked(int P);
    }

    ArrayList<HistoryList>inventory;
    Context C;
    ItemListener listener;

    public HistoryRecycleAdapter(ArrayList<HistoryList> inventory, Context C){
        this.inventory = inventory;
        this.C = C;
    }

    @NonNull
    @Override
    public HistoryRecycleAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(C).inflate(R.layout.history_recycler,parent, false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryRecycleAdapter.myViewHolder holder, int position) {

        holder.historyType.setText(inventory.get(position).getProductName());
        holder.historyPrice.setText(String.valueOf(inventory.get(position).getPrice()));
        holder.historyQuantity.setText(String.valueOf(inventory.get(position).getQuantity()));

    }

    @Override
    public int getItemCount() {
        return inventory.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView historyType;
        TextView historyQuantity;
        TextView historyPrice;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            historyType = itemView.findViewById(R.id.hrt_name);
            historyQuantity = itemView.findViewById(R.id.hrt_qty);
            historyPrice = itemView.findViewById(R.id.hrt_price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {listener.onItemClicked(getAdapterPosition());

        }
    }
}
