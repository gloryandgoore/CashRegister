package com.example.cashregister;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    ArrayList<StoreItem> inventory;
    ArrayList<HistoryList> historyList = new ArrayList<>();
    int item_index = 0;
    ListView item_list;
    TextView quantityTV, productTypeTV, totalTV;
    MaterialButton buttonC;
    MaterialButton buttonManager;
    MaterialButton buttonBuy;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    String MERGE = "";
    String regex = "[0-9]+";

    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inventory = new ArrayList<>();
        item_list = findViewById(R.id.item_list);
        item_list.setOnItemClickListener(this);

        inventory = ((StoreHistory) getApplication()).setProductListData();

        ImplementAdaptor();

        productTypeTV = findViewById(R.id.product_type);
        totalTV = findViewById(R.id.total);
        quantityTV = findViewById(R.id.quantity);

        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonManager, R.id.btn_manager);
        assignId(buttonC, R.id.button_C);
        assignId(buttonBuy, R.id.btn_buy);

        //Buy button
//        buttonManager.setOnClickListener(view -> {
//            ((StoreHistory) getApplication()).setData(historyList);
//            ((StoreHistory) getApplication()).setSize(historyList.size());
//
//            Intent firstIntent = new Intent(MainActivity.this, ManagerActivity.class);
//            startActivity(firstIntent);
//
//        });
//
//        buttonC.setOnClickListener(view -> {
//            quantityTV.setText("");
//            MERGE = "";
//            totalTV.setText("");
//            productTypeTV.setText("");
//        });

    }

    //1) findViewByID for each button
    //2) set on click listener for each button
    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        HistoryList h1;
        if(buttonText.equals("C")){
            quantityTV.setText("");
            MERGE = "";
            totalTV.setText("");
            productTypeTV.setText("");

        } else if (buttonText.matches(regex)) {
            MERGE = MERGE + buttonText;

            quantityTV.setText(MERGE);

        }
        if (buttonText.equals("Buy")) {

            double TotalPrice = Double.parseDouble(quantityTV.getText().toString()) * inventory.get(item_index).getPrice();
            int newQty = (inventory.get(item_index).quantity - Integer.parseInt(quantityTV.getText().toString()));
            inventory.set(item_index,
                    new StoreItem(inventory.get(item_index).getProductName(), inventory.get(item_index).getPrice(), newQty > 0 ? newQty : 0));
            if (newQty < 0) {
                Toast.makeText(this, "Wrong qty.", Toast.LENGTH_LONG).show();
            } else {
                showAlert(TotalPrice, quantityTV.getText().toString());
                ImplementAdaptor();

                totalTV.setText("" + TotalPrice);

                h1 = new HistoryList(inventory.get(item_index).getProductName(), TotalPrice, Integer.parseInt(quantityTV.getText().toString()), new Date().toString());

                historyList.add(h1);
            }
        }
        if (buttonText.equals("Manager")) {

            ((StoreHistory) getApplication()).setData(historyList);
            ((StoreHistory) getApplication()).setSize(historyList.size());

            Intent firstIntent = new Intent(MainActivity.this, ManagerActivity.class);
            startActivity(firstIntent);
        }

    }
    //updated data to main screen

    private void setListData() {
        ((StoreHistory) getApplication()).setProductListData();

        inventory = ((StoreHistory) getApplication()).getProductList();

        adapter = new ItemAdapter( MainActivity.this, inventory);
        item_list.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        productTypeTV.setText(inventory.get(position).getProductName());
        item_index = position;

    }

    private void showAlert(double TotalPrice, String Qty) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your purchase is " + Qty + " " + inventory.get(item_index).getProductName() +
                        " for $" + String.format("%.2f", TotalPrice))
                .setTitle("Thank You for your purchase");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MaterialButton btnClickMe = (MaterialButton) findViewById(R.id.button_C);
                btnClickMe.performClick();

            }
        });
        builder.show();
    }
    //update listview
    private void ImplementAdaptor() {
        adapter = new ItemAdapter(MainActivity.this, inventory);
        item_list.setAdapter((ListAdapter) adapter);
    }

    //update list view/history view

    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();

    }
}