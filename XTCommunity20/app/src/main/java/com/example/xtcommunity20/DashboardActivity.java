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
    ListView lsvDashboardPosts;
    TextView tvwText;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Intent intent = getIntent();
        userID = intent.getStringExtra("id");
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
        tvwText = (TextView) findViewById(R.id.textViewTest);

        tvwText.setText(userID);
    }

    private void actionListener() {

    }
    //Functional Methods
}