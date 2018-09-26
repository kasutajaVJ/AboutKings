package com.example.keiti.aboutkings;

public class King {

    private String name, text;
    private int from, to;

    public King(String name, int from, int to, String text) {
        this.name = name;
        this.text = text;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public String getText() {
        return text;
    }

    public String getKing() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
