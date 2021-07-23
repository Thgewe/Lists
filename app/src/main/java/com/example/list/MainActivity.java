package com.example.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView itemsView;

    private ItemsAdapter itemsAdapter = new ItemsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureRecyclerView();

        generateItem();
    }

    private void generateItem() {
        List<Item> item = new ArrayList<>();

        item.add(new Item("milk", "50"));
        item.add(new Item("eggs", "100"));
        item.add(new Item("meat", "500"));
        item.add(new Item("pan", "2000"));
        item.add(new Item("milk", "50"));
        item.add(new Item("eggs", "100"));
        item.add(new Item("meat", "500"));
        item.add(new Item("pan", "2000"));
        item.add(new Item("milk", "50"));
        item.add(new Item("eggs", "100"));
        item.add(new Item("meat", "500"));
        item.add(new Item("pan", "2000"));
        item.add(new Item("milk", "50"));
        item.add(new Item("eggs", "100"));
        item.add(new Item("meat", "500"));
        item.add(new Item("pan", "2000"));
        item.add(new Item("milk", "50"));
        item.add(new Item("eggs", "100"));
        item.add(new Item("meat", "500"));
        item.add(new Item("pan", "2000"));

        itemsAdapter.setData(item);
    }

    private void configureRecyclerView() {
        itemsView = findViewById(R.id.itemsView);
        itemsView.setAdapter(itemsAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);

        itemsView.setLayoutManager(layoutManager);
    }
}