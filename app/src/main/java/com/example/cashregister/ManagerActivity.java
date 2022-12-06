package com.example.cashregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class ManagerActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialButton History, Restock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        assignId(History, R.id.button_history);
        assignId(Restock, R.id.button_restock);
    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();


        if (buttonText.equals("History")) {
            Intent historyIntent = new Intent(ManagerActivity.this, HistoryActivity.class);
            startActivity(historyIntent);
        }

        if (buttonText.equals("Restock")) {
            Intent restockIntent = new Intent(ManagerActivity.this, RestockActivity.class);
            startActivity(restockIntent);
        }

    }
}
