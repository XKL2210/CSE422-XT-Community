package com.example.xtcommunity20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.Gender;
import model.Post;
import model.PostType;
import model.User;

public class DashboardActivity extends AppCompatActivity {
    private ListView lsvDashboardPosts;
    private RelativeLayout rltDashboardProfile, rltDashboardSearch;
    private ImageView imvDashboardHome, imvDashboardUser
            , imvDashboardSearch, imvDashboardCreate
            , imvDashboardRelated, imvDashboardLogout
            , imvDashboardSearchEntrance;
    private TextView tvwDashboardProfileName, tvwDashboardProfileGender
            , tvwDashboardProfileEmail, tvwDashboardProfileMobile;
    private EditText edtDashboardSearch;
    private String coreUsername, coreFullName
            , coreGender, coreDateOfBirth
            , coreEmail, coreMobile
            , userId, searchContext;
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
        setUpGUI();
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
        imvDashboardCreate = (ImageView) findViewById(R.id.imvDashboardCreate);
        imvDashboardRelated = (ImageView) findViewById(R.id.imvDashboardRelated);
        imvDashboardLogout = (ImageView) findViewById(R.id.imvDashboardLogout);
        imvDashboardSearchEntrance = (ImageView) findViewById(R.id.imvDashboardSearchEntrance);
        tvwDashboardProfileName = (TextView) findViewById(R.id.tvwDashboardProfileName);
        tvwDashboardProfileGender = (TextView) findViewById(R.id.tvwDashboardProfileGender);
        tvwDashboardProfileEmail = (TextView) findViewById(R.id.tvwDashboardProfileEmail);
        tvwDashboardProfileMobile = (TextView) findViewById(R.id.tvwDashboardProfileMobile);
        edtDashboardSearch = (EditText) findViewById(R.id.edtDashboardSearch);
    }

    private void setUpGUI() {
        homeOnClickAction();
        getCoreVariables();
        listViewSetUp(corePosts);
        tvwDashboardProfileName.setText(coreUsername + " (" + coreFullName + ")");
        tvwDashboardProfileEmail.setText(coreEmail);
        tvwDashboardProfileGender.setText(coreGender + " " + coreDateOfBirth);
        tvwDashboardProfileMobile.setText(coreMobile);
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

        imvDashboardCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNewPost();
            }
        });

        imvDashboardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imvDashboardRelated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relatedListview();
            }
        });

        imvDashboardSearchEntrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchListview();
            }
        });
    }

    //Functional Methods
    private void getCoreVariables() {
        corePosts = database.getAllPost(PostType.Question);
        currentUser = database.getUserById(userId);
        coreFullName = currentUser.getFullName();
        if(currentUser.getGender().equals(Gender.Female)) {
            coreGender = "Female";
        } else { coreGender = "Male"; }
        coreUsername = currentUser.getUsername();
        coreDateOfBirth = currentUser.getDateOfBirth();
        coreEmail = currentUser.getEmail();
        coreMobile = currentUser.getPhone();
    }

    private void getPassingID() {
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
    }

    private void listViewSetUp(List<Post> posts) {
        PostApdapter adapter = new PostApdapter(this, posts);
        adapter.setUserId(userId);
        lsvDashboardPosts.setAdapter(adapter);
    }

    private void relatedListview() {
        homeOnClickAction();
        List<Post> relatedPosts = new ArrayList<>();
        relatedPosts = database.getPostByUser(userId);
        listViewSetUp(relatedPosts);
    }

    private void searchListview() {
        homeOnClickAction();
        searchContext = edtDashboardSearch.getText().toString().trim();
        if(TextUtils.isEmpty(searchContext))
        {
            edtDashboardSearch.setError("Advise filling the form");
            return;
        }

        List<Post> relatedPosts = new ArrayList<>();
        relatedPosts = database.getPostByContext(searchContext);
        listViewSetUp(relatedPosts);
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

    private void toNewPost() {
        Intent intent = new Intent(DashboardActivity.this, NewPostsActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        objectsInitialization();
        getCoreVariables();
        listViewSetUp(corePosts);
    }
}