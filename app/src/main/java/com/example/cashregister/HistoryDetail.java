package com.example.cashregister;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class HistoryDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);
        TextView itemTV = (TextView) findViewById(R.id.detail_type);
        TextView priceTV = (TextView) findViewById(R.id.detail_price);
        TextView dateTV = (TextView) findViewById(R.id.detail_date);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        if(getIntent().hasExtra("Index")) {
            int index = getIntent().getIntExtra("Index", 0);
            HistoryList h2 = ((StoreHistory) getApplication()).getData(index);
            Toast.makeText(this, "index: "+index, Toast.LENGTH_SHORT).show();
//make price a double
            itemTV.setText("Product: " + h2.getProductName() + "\n" +"Price: "+ String.format("%.2f", h2.getPrice()) +"\n" +"Purchase_date: "+ h2.getDate());


        }



    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), HistoryActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

}
