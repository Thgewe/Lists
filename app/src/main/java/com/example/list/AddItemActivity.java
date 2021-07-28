package com.example.list;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

        titleText = findViewById(R.id.edittext_additem_title);
        valueText = findViewById(R.id.edittext_additem_value);
        addButton = findViewById(R.id.button_additem_add);

        addButton.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(titleText.getText()) && !TextUtils.isEmpty(valueText.getText())) {
                Intent intent = new Intent();
                intent.putExtra("title", titleText.getText().toString());
                intent.putExtra("value", valueText.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
