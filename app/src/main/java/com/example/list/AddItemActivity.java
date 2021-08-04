package com.example.list;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AddItemActivity extends AppCompatActivity {

    private Button addButton;
    private EditText titleText;
    private EditText valueText;
//    private String type;

//    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        titleText = findViewById(R.id.edittext_additem_title);
        valueText = findViewById(R.id.edittext_additem_value);
        addButton = findViewById(R.id.button_additem_add);
//        type = getIntent().getExtras().getString("type");
        addButton.setOnClickListener(v -> {
            if (titleText.getText().equals("") || valueText.getText().equals("")) {
                Toast.makeText(getApplicationContext(), getString(R.string.fill_fields), Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent();
//                putRemoteItem();
                intent.putExtra("name", titleText.getText().toString());
                intent.putExtra("price", Integer.parseInt(valueText.getText().toString()));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
//        compositeDisposable.dispose();
        super.onDestroy();
    }

//    public void putRemoteItem() {
//        Disposable disposable = ((LoftApp) getApplication()).moneyApi.postMoney(
//                Integer.parseInt(valueText.getText().toString()),
//                titleText.getText().toString(),
//                type
//        )
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(() -> {
//                    Toast.makeText(getApplicationContext(), getString(R.string.success_added), Toast.LENGTH_LONG).show();
//                }, throwable -> {
//                    Toast.makeText(getApplicationContext(), throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//                });
//        compositeDisposable.add(disposable);
//    }
}
