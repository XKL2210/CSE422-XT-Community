package com.example.xtcommunity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    ListView lsvDashQuestions;
    Database database;
    ArrayList<Post> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        constrainsGUI();
        database = new Database();
        list = database.getAllPost();
        PostAdapter adapter = new PostAdapter(DashboardActivity.this, database.getAllPost());
        lsvDashQuestions.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void constrainsGUI() {
        lsvDashQuestions = (ListView) findViewById(R.id.lsvDashQuestions);
    }

    private void actionListener() {

    }
}