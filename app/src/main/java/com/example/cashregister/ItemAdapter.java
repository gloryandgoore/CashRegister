package com.example.cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    MainActivity mainActivity;
    ArrayList<StoreItem> inventory;
    LayoutInflater layoutInflater;

    public ItemAdapter(MainActivity mainActivity, ArrayList<StoreItem> inventory) {
        this.mainActivity = mainActivity;
        this.inventory = inventory;
        layoutInflater = (LayoutInflater) mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View rowView = view;

        if(rowView == null) {
            rowView = layoutInflater.inflate(R.layout.list, null, true);
        }

        TextView list_item1 = rowView.findViewById(R.id.list_item1);
        list_item1.setText(inventory.get(i).getProductName());
        TextView list_item2 = rowView.findViewById(R.id.list_item2);
        list_item2.setText(String.valueOf(inventory.get(i).getPrice()));
        TextView list_item3 = rowView.findViewById(R.id.list_item3);
        list_item3.setText(String.valueOf(inventory.get(i).getQuantity()));

        return rowView;
    }
}
