package com.example.cashregister;

import android.app.Application;

import java.util.ArrayList;

public class StoreHistory extends Application {
    private ArrayList<HistoryList> historyListData = new ArrayList<>();
    private ArrayList<StoreItem> productList = new ArrayList<>(0);
    private int purchaseHistorySize = 0;

    public int getSize(){
        return  purchaseHistorySize;
    }

    public void setSize(int size){
        this.purchaseHistorySize = size;
    }

    public void setData(ArrayList<HistoryList> data){
        this.historyListData = data;
    }

    public ArrayList<HistoryList>  getData(){
        return this.historyListData;
    }

    public HistoryList  getData(int index){
        return this.historyListData.get(index);
    }

    public ArrayList<StoreItem>   getProductList(){
        return this.productList;
    }

    public ArrayList<StoreItem> setProductListData(){
        if(productList.isEmpty()){

            //add items to store
            StoreItem item_1 = new StoreItem("Pants",20.45, 10);
            StoreItem item_2= new StoreItem("Shoes",10.67, 100);
            StoreItem item_3=new StoreItem("Hats",5.92, 30);

            productList.add(item_1);
            productList.add(item_2);
            productList.add(item_3);
        }
        return this.productList;
    }
}

