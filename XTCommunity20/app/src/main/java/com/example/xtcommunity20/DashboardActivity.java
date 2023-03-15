package com.example.xtcommunity20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.Post;
import model.User;

public class DashboardActivity extends AppCompatActivity {
    private ListView lsvDashboardPosts;
    private RelativeLayout rltDashboardProfile, rltDashboardSearch;
    private ImageView imvDashboardHome, imvDashboardUser
            , imvDashboardSearch;
    private String coreUsername, coreFullName
            , coreGender, coreDateOfBirth
            , coreEmail, coreMobile
            , userID;
    private List<Post> corePosts;
    private Database database;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        constrainGUI();
        objectsInitialization();
        getPassingID();
        getCoreVariables();
        listViewSetUp();
        actionListener();
    }

    //Initialization
    private void constrainGUI() {
        lsvDashboardPosts = (ListView) findViewById(R.id.lsvDashboardList);
        rltDashboardProfile = (RelativeLayout) findViewById(R.id.rltDashboardProfile);
        rltDashboardSearch = (RelativeLayout) findViewById(R.id.rltDashboardSearch);
        imvDashboardHome = (ImageView) findViewById(R.id.imvDashboardHome);
        imvDashboardUser = (ImageView) findViewById(R.id.imvDashboardUserProfile);
        imvDashboardSearch = (ImageView) findViewById(R.id.imvDashboardSearch);
    }

    private void setUpGUI() {
        homeOnClickAction();
    }
    private void objectsInitialization() {
        corePosts = new ArrayList<Post>();
        database = new Database();
    }

    private void actionListener() {
        imvDashboardHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeOnClickAction();
            }
        });

        imvDashboardSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchOnClickAction();
            }
        });

        imvDashboardUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userOnClickAction();
            }
        });
    }

    //Functional Methods
    private void getCoreVariables() {
        corePosts = database.getAllPost();
        currentUser = database.getUserById(userID);
        coreFullName = currentUser.getFullName();
        coreGender = currentUser.getDateOfBirth();
        coreUsername = currentUser.getUsername();
        coreDateOfBirth = currentUser.getDateOfBirth();
        coreEmail = currentUser.getEmail();
        coreMobile = currentUser.getPhone();
    }

    private void getPassingID() {
        Intent intent = getIntent();
        userID = intent.getStringExtra("id");
    }

    private void listViewSetUp() {
        PostApdapter adapter = new PostApdapter(this, corePosts);
        lsvDashboardPosts.setAdapter(adapter);
    }

    private void homeOnClickAction() {
        lsvDashboardPosts.setVisibility(View.VISIBLE);
        rltDashboardProfile.setVisibility(View.GONE);
        rltDashboardSearch.setVisibility(View.GONE);
    }

    private void userOnClickAction() {
        lsvDashboardPosts.setVisibility(View.GONE);
        rltDashboardProfile.setVisibility(View.VISIBLE);
        rltDashboardSearch.setVisibility(View.GONE);
    }

    private void searchOnClickAction() {
        lsvDashboardPosts.setVisibility(View.GONE);
        rltDashboardProfile.setVisibility(View.GONE);
        rltDashboardSearch.setVisibility(View.VISIBLE);
    }
}