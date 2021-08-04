package com.example.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.list.remote.MoneyRemoteItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class BudgetFragment extends Fragment {

    public static final int REQUEST_CODE = 0;
    public static final String ARGS_CURRENT_POSITION = "args_current_position";

    private Button addButton;
    private RecyclerView recyclerView;
    private ItemsAdapter itemsAdapter = new ItemsAdapter();
    private List<Item> itemModels = new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String type;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadItems();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
                             @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_budget, null);
        recyclerView = view.findViewById(R.id.itemsView);
        addButton = view.findViewById(R.id.button_main_add);

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddItemActivity.class);
            type = getArguments().getString(ARGS_CURRENT_POSITION);
            startActivityForResult(intent, REQUEST_CODE);
        });

        recyclerView.setAdapter(itemsAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);

        itemsAdapter.setData(itemModels);
        return view;
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        putRemoteItem(data.getStringExtra("name"), data.getIntExtra("price", 0));
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static BudgetFragment newInstance(String currentPos) {
        BudgetFragment fragment = new BudgetFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_CURRENT_POSITION, currentPos);
        fragment.setArguments(args);
        return fragment;
    }

    public void loadItems() {
        compositeDisposable.add(((LoftApp) getActivity().getApplication()).moneyApi.getMoneyItems(getArguments().getString(ARGS_CURRENT_POSITION))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moneyResponse -> {
                    if (moneyResponse.getStatus().equals("success")) {
                        for (MoneyRemoteItem moneyRemoteItem : moneyResponse.getMoneyItemsList()) {
                            itemModels.add(Item.getInstance(moneyRemoteItem));
                        }
                        itemsAdapter.setData(itemModels);
                        itemModels.clear();
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(),getString(R.string.connection_lost), Toast.LENGTH_LONG).show();
                    }
                }, throwable -> {
                    Toast.makeText(getActivity().getApplicationContext(),throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }));
    }

    public void putRemoteItem(String name, int price) {
        compositeDisposable.add(((LoftApp) getActivity().getApplication()).moneyApi.postMoney(
                price,
                name,
                type
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Toast.makeText(getActivity().getApplicationContext(), getString(R.string.success_added), Toast.LENGTH_LONG).show();
                }, throwable -> {
                    Toast.makeText(getActivity().getApplicationContext(), throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }));
    }
}
