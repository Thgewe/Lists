package com.example.list;

import android.widget.Toast;

import com.example.list.remote.MoneyRemoteItem;

public class Item {
    private String title;
    private String value;
    private int currentPos;

    public Item(String title, String value, int currentPos) {
        this.title = title;
        this.value = value;
        this.currentPos = currentPos;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public static Item getInstance(MoneyRemoteItem moneyRemoteItem) {
        if (moneyRemoteItem.getType().equals("expense")) {
            return new Item(moneyRemoteItem.getName(), moneyRemoteItem.getPrice() + "$", 0);
        } else {
            return new Item(moneyRemoteItem.getName(), moneyRemoteItem.getPrice() + "$", 1);
        }
    }
}
