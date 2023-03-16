package com.example.xtcommunity20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import database.Database;
import model.Post;
import model.PostType;

public class NewPostsActivity extends AppCompatActivity {
    private EditText edtNewPost;
    private ImageView imvNewPost;
    private Database database;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_posts);
        constrainGUI();
        objectInitialization();
        actionListener();
    }

    //Initialization
    private void constrainGUI() {
        edtNewPost = (EditText) findViewById(R.id.edtNewPost);
        imvNewPost = (ImageView) findViewById(R.id.imvNewPost);
    }

    private void objectInitialization() {
        database = new Database();
    }

    private void actionListener() {
        imvNewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuestion();
            }
        });
    }

    private void addQuestion() {
        String coreQuestion = edtNewPost.getText().toString().trim();
        if(TextUtils.isEmpty(coreQuestion)) {
            edtNewPost.setError("Advise filling the form");
            return;
        }

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

        database.createNewPost(new Post(userId, "None", PostType.Question, coreQuestion, 0, 0));
        finish();
    }
}