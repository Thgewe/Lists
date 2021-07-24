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
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView itemsView;
    private Button addButton;
    private ItemsAdapter itemsAdapter = new ItemsAdapter();
    List<Item> item = new ArrayList<>();
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.button_main_add);
        configureRecyclerView();
        Intent intent = new Intent(this, AddItemActivity.class);
        addButton.setOnClickListener(v -> {
            activityResultLauncher.launch(intent);
        });

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    item.add(new Item(result.getData().getStringExtra("title"), result.getData().getStringExtra("value")));
                    itemsAdapter.setData(item);
                    generateItem();
                }
            }
        });
    }

    private void generateItem() {
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