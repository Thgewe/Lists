package com.example.list;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private RecyclerView itemsView;
//    private Button addButton;
//    private ItemsAdapter itemsAdapter = new ItemsAdapter();
//    List<Item> item = new ArrayList<>();
//    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabs = findViewById(R.id.tabs);
        ViewPager pages = findViewById(R.id.pages);
        pages.setAdapter(new BudgetPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        
        tabs.setupWithViewPager(pages);
        tabs.getTabAt(0).setText("Expense");
        tabs.getTabAt(1).setText("Income");
//        addButton = findViewById(R.id.button_main_add);
//        configureRecyclerView();
//        addButton.setOnClickListener(v -> {
//            Intent intent = new Intent(this, AddItemActivity.class);
//            activityResultLauncher.launch(intent);
//        });
//
//        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//            @Override
//            public void onActivityResult(ActivityResult result) {
//                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
//                    item.add(new Item(result.getData().getStringExtra("title"), result.getData().getStringExtra("value")));
//                    itemsAdapter.setData(item);
//                    generateItem();
//                }
//            }
//        });
    }
//    private void generateItem() {
//        itemsAdapter.setData(item);
//    }
//
//    private void configureRecyclerView() {
//        itemsView = findViewById(R.id.itemsView);
//        itemsView.setAdapter(itemsAdapter);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
//                LinearLayoutManager.VERTICAL, false);
//
//        RecyclerView.ItemDecoration itemDecor = new DividerItemDecoration(itemsView.getContext(),
//                LinearLayoutManager.VERTICAL);
//        itemsView.addItemDecoration(itemDecor);
//        itemsView.setLayoutManager(layoutManager);
//    }


    static class BudgetPagerAdapter extends FragmentPagerAdapter {
        public BudgetPagerAdapter(@NonNull @NotNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment getItem(int position) {
            if (position < 2) {
                return BudgetFragment.newInstance(position);
            } else {
                return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}