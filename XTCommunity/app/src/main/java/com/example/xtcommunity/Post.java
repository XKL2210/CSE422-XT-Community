package com.example.xtcommunity;

import java.time.LocalDate;
import java.util.UUID;

public class Post {
    private String id;
    private String title;
    private String numAnswers;
    private User owner;
    private LocalDate date;
    private int upvotes;
    private int downvotes;

    public Post(String title, String numAnswers, User owner, int upvotes, int downvotes) {
        this.title = title;
        this.numAnswers = numAnswers;
        this.owner = owner;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.date = LocalDate.now();
        this.id = UUID.randomUUID().toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumAnswers() {
        return numAnswers;
    }

    public void setNumAnswers(String numAnswers) {
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
}
