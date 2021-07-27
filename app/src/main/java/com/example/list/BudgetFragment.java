package com.example.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BudgetFragment extends Fragment {

    public static final int REQUEST_CODE = 0;
    public static final String ARGS_CURRENT_POSITION = "args_current_position";

    private Button addButton;
    private RecyclerView recyclerView;
    private ItemsAdapter itemsAdapter = new ItemsAdapter();
    private List<Item> itemModels = new ArrayList<>();
    private int currentPos;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            currentPos = getArguments().getInt(ARGS_CURRENT_POSITION);
        }
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
                             @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_budget, null);
        recyclerView = view.findViewById(R.id.itemsView);
        itemsAdapter.setData(itemModels);
        addButton = view.findViewById(R.id.button_main_add);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddItemActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });

        recyclerView.setAdapter(itemsAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String nameAdd = data.getStringExtra("title");
        String priceAdd = data.getStringExtra("value");

        itemModels.add(new Item(nameAdd, priceAdd, currentPos));
        itemsAdapter.setData(itemModels);
    }

    public static BudgetFragment newInstance(int currentPos) {
        BudgetFragment fragment = new BudgetFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_CURRENT_POSITION, currentPos);
        fragment.setArguments(args);
        return fragment;
    }
}
