package com.example.cashregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements HistoryRecycleAdapter.ItemListener {
//ListView itemList;
//ArrayList<Purchase> purchaseArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        int historySize =((StoreHistory) getApplication()).getSize();
        RecyclerView recyclerView=findViewById(R.id.history_RV);
        HistoryRecycleAdapter adapter=new HistoryRecycleAdapter(((StoreHistory) getApplication()).getData(),this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        adapter.listener = this;
        recyclerView.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ManagerActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    @Override
    public void onItemClicked(int P) {
        gotoNextActivity(P);
    }

    private void gotoNextActivity(int P) {

        Intent intent=new Intent(HistoryActivity.this, HistoryDetail.class);
        intent.putExtra("Index", P);
        startActivity(intent);
    }
}


