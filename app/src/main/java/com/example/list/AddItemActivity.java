package com.example.list;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    private Button addButton;
    private EditText titleText;
    private EditText valueText;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        addButton = findViewById(R.id.button_additem_add);
        titleText = findViewById(R.id.edittext_additem_title);
        valueText = findViewById(R.id.edittext_additem_value);

        addButton.setOnClickListener(v -> {
            Intent i = new Intent();
            if (titleText.getText() != null && valueText.getText() != null && !TextUtils.isEmpty(titleText.getText()) && !TextUtils.isEmpty(valueText.getText())) {
                i.putExtra("title", titleText.getText().toString());
                i.putExtra("value", valueText.getText().toString());
                setResult(RESULT_OK, i);
                finish();
                }
        });
    }
}
