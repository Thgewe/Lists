package com.example.list;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView itemsView;
    private Button addButton;
    private ItemsAdapter itemsAdapter = new ItemsAdapter();
    ActivityResultLauncher<Intent> activityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.button_main_add);
        configureRecyclerView();
        Intent intent = new Intent(this, AddItemActivity.class);
        generateItem();


        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {

                activityResultLauncher.launch(intent);
            }
        };
        addButton.setOnClickListener(onClickListener);
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

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    item.add(new Item(result.getData().getStringExtra("title"), result.getData().getStringExtra("value")));
                    itemsAdapter.setData(item);
                }
            }
        });


        itemsAdapter.setData(item);
    }

    private void configureRecyclerView() {
        itemsView = findViewById(R.id.itemsView);
        itemsView.setAdapter(itemsAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);

        RecyclerView.ItemDecoration itemDecor = new DividerItemDecoration(itemsView.getContext(),
                LinearLayoutManager.VERTICAL);
        itemsView.addItemDecoration(itemDecor);
        itemsView.setLayoutManager(layoutManager);
    }

}