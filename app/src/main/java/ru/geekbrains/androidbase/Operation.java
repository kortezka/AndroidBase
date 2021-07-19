package ru.geekbrains.androidbase;

public enum Operation {
    ADDITION("+"),
    MULTIPLICATION("*"),
    SUBTRACTION("-"),
    DIVIDE("/");

    private String title;

    Operation(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
