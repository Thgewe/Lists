package com.example.list;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

    private String mPrice;
    private String mName;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        titleText = findViewById(R.id.edittext_additem_title);
        valueText = findViewById(R.id.edittext_additem_value);
        if (getIntent().getIntExtra("color", 2) == 0) {
            titleText.setTextColor(Color.parseColor("#4A90E2"));
            valueText.setTextColor(Color.parseColor("#4A90E2"));
        } else {
            titleText.setTextColor(Color.parseColor("#7ED321"));
            valueText.setTextColor(Color.parseColor("#7ED321"));
        };

        titleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                mName = s.toString();
                checkEditTextHasText();
            }
        });


        valueText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPrice = s.toString();
                checkEditTextHasText();
            }
        });
        addButton = findViewById(R.id.button_additem_add);
        checkEditTextHasText();
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("name", mName);
            intent.putExtra("price", Integer.parseInt(mPrice));
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    public void checkEditTextHasText() {
        addButton.setEnabled(!TextUtils.isEmpty(mName) && !TextUtils.isEmpty(mPrice));
    }

}
