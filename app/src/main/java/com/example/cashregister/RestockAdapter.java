package com.example.cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RestockAdapter extends BaseAdapter {
Context context;
private final ArrayList<StoreItem> inventory;
private final LayoutInflater inflater;



public RestockAdapter(ArrayList<StoreItem> inventory, Context context){
    this.context = context;
    this.inventory = inventory;
    inflater = (LayoutInflater.from(context));
}

    @Override
    public int getCount() {
        return inventory.size();
    }

    @Override
    public Object getItem(int i) {
        return inventory.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    View v = inflater.inflate(R.layout.list, viewGroup, false);

        TextView productTypeTV = v.findViewById(R.id.list_item1);
        TextView quantityTV = v.findViewById(R.id.list_item3);
        TextView totalTV = v.findViewById(R.id.list_item2);

        productTypeTV.setText(inventory.get(i).getProductName());
        quantityTV.setText(String.valueOf(inventory.get(i).getQuantity()));
        totalTV.setText(String.valueOf(inventory.get(i).getPrice()));

        return v;
    }
}
