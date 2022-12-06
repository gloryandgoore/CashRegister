package com.example.cashregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class RestockActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    ListView itemToRestock;
    MaterialButton button_ok, button_cancel;
    EditText newQuantity;
    RestockAdapter adapter;
    int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);
        initializeAllViews();
        setInventoryData();

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        button_ok.setOnClickListener(this);
        button_cancel.setOnClickListener(this);
        itemToRestock.setOnItemClickListener(this);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ManagerActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    private void setInventoryData(){
        adapter = new RestockAdapter(((StoreHistory) getApplication()).getProductList(), this);
        itemToRestock.setAdapter(adapter);
    }

    private void initializeAllViews(){
        newQuantity = findViewById(R.id.modifyQuantity);
        button_ok = findViewById(R.id.restockOkButton);
        button_cancel = findViewById(R.id.restockCancelButton);
        itemToRestock = findViewById(R.id.restockListView);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.restockOkButton){
            if(newQuantity.getText().toString().equals("") || selectedIndex == -1){
                Toast.makeText(this, "All fields are required.", Toast.LENGTH_SHORT).show();
            } else {
                StoreItem item_1 = ((StoreHistory)getApplication()).getProductList().get(selectedIndex);
                item_1.setQuantity(item_1.getQuantity()+Integer.parseInt(newQuantity.getText().toString()));
                ((StoreHistory)getApplication()).getProductList().set(selectedIndex, item_1);
                adapter.notifyDataSetChanged();
            }
        }

        else {
            finish();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedIndex = position;
        Toast.makeText(this, ((StoreHistory) getApplication()).getProductList().get(position).getProductName(), Toast.LENGTH_SHORT).show();

    }
}
