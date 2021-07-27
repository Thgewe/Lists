package com.example.list;

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
}
