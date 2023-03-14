package com.example.xtcommunity20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.Post;

public class DashboardActivity extends AppCompatActivity {
    private ListView lsvDashboardPosts;
    private String userID;
    private List<Post> corePosts;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        constrainGUI();
        actionListener();
    }

    //Initialization
    private void constrainGUI() {
        lsvDashboardPosts = (ListView) findViewById(R.id.lsvDashboardList);
    }

    private void objectsInitialization() {
        corePosts = new ArrayList<Post>();
        database = new Database();
    }

    private void actionListener() {

    }
    //Functional Methods
    private void getCoreVariables() {
        corePosts = database.getAllPost();
    }

    private void getPassingID() {
        Intent intent = getIntent();
        userID = intent.getStringExtra("id");
    }

    private void listViewSetUp() {
        PostApdapter adapter = new PostApdapter(this, corePosts);
        lsvDashboardPosts.setAdapter(adapter);
    }
}