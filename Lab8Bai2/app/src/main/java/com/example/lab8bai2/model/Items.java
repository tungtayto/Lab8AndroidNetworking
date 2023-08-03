package com.example.lab8bai2.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Items {
    private String title;
    public String uid;
    public String author;
    public boolean isDone;

    public Items() {
    }

    public Items(String title, String uid, String author, boolean isDone) {
        this.title = title;
        this.uid = uid;
        this.author = author;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("title", title);
        result.put("isDone", isDone);
        return result;
    }
}
