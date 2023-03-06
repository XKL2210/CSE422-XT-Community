package com.example.xtcommunity;

import android.os.Build;

import java.time.LocalDate;
import java.util.UUID;

public class Post {
    private String id;
    private String title;
    private int numAnswers;
    private User owner;
    private LocalDate date;
    private int upvotes;
    private int downvotes;

    public Post(String title, int numAnswers, User owner, int upvotes, int downvotes) {
        this.title = title;
        this.numAnswers = numAnswers;
        this.owner = owner;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.id = UUID.randomUUID().toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumAnswers() {
        return numAnswers;
    }

    public void setNumAnswers(int numAnswers) {
        this.numAnswers = numAnswers;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public String getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
