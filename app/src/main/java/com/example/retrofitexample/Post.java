package com.example.retrofitexample;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int userId;
    private int id;
    private int title;
    @SerializedName("body")
    private int text;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public int getTitle() {
        return title;
    }

    public int getText() {
        return text;
    }
}
