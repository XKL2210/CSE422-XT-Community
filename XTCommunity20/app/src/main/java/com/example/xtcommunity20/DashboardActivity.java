package com.example.xtcommunity20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.Post;

public class DashboardActivity extends AppCompatActivity {
    ListView lsvDashboardPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        constrainsGUI();
        actionListener();

        List<Post> posts = new ArrayList<Post>();
        Database database = new Database();
        posts = database.getAllPost();
        PostApdapter adapter = new PostApdapter(this, posts);
        lsvDashboardPosts.setAdapter(adapter);
    }
    //Initialization
    private void constrainsGUI() {
        lsvDashboardPosts = (ListView) findViewById(R.id.lsvDashboardList);
    }

    private void actionListener() {

    }
    //Functional Methods
}