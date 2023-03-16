package com.example.xtcommunity20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.Gender;
import model.Post;
import model.PostType;
import model.User;

public class SinglePostActivity extends AppCompatActivity {
    private TextView tvwSingleTitle, tvwSingleUser
            , tvwSingleUpvote, tvwSingleDownvote;
    private ImageView imvSingleUpvote, imvSingleDownvote
            , imvSingleAddComment;
    private EditText edtSingleComment;
    private ListView lsvSingleList;
    private String coreTitle, coreUser
            , coreUpvote, coreDownvote
            , coreComment, postId
            , userId;
    private Post originalPost;
    private List<Post> posts;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);
        constrainGUI();
        objectsInitialization();
        getPassingID();
        getCoreVariables();
        setUpGUI();
        actionListener();
    }

    //Initialization
    private void constrainGUI() {
        tvwSingleTitle = (TextView) findViewById(R.id.tvwSingleTitle);
        tvwSingleUser = (TextView) findViewById(R.id.tvwSingleUser);
        tvwSingleUpvote = (TextView) findViewById(R.id.tvwSingleUpvote);
        tvwSingleDownvote = (TextView) findViewById(R.id.tvwSinglemDownvote);
        imvSingleUpvote = (ImageView) findViewById(R.id.imvSingleUpvote);
        imvSingleDownvote = (ImageView) findViewById(R.id.imvSingleDownvote);
        imvSingleAddComment = (ImageView) findViewById(R.id.imvSingleAddComment);
        edtSingleComment = (EditText) findViewById(R.id.edtSingleComment);
        lsvSingleList = (ListView) findViewById(R.id.lsvSingleComment);
    }

    private void setUpGUI() {
        listViewSetUp(posts);
        tvwSingleTitle.setText(coreTitle);
        tvwSingleUser.setText(coreUser);
        tvwSingleUpvote.setText(coreUpvote);
        tvwSingleDownvote.setText(coreDownvote);
    }

    private void objectsInitialization() {
        database = new Database();
        posts = new ArrayList<>();
    }

    private void actionListener() {
        imvSingleUpvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.upvotePost(postId);
                listViewSetUp(posts);
            }
        });

        imvSingleDownvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.downvotePost(postId);
                listViewSetUp(posts);
            }
        });

        imvSingleAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewComment();
            }
        });
    }

    //Functional Methods
    private void getCoreVariables() {
        originalPost = database.getPostById(postId);
        coreTitle = originalPost.getContext();
        coreUser = "User: " + database.getUserById(originalPost.getOwner()).getUsername();
        coreUpvote = String.valueOf(originalPost.getUpvotes());
        coreDownvote = String.valueOf(originalPost.getDownvotes());
        coreComment = edtSingleComment.getText().toString().trim();
        posts = database.getPostByOrigin(originalPost.getId());
    }

    private void getPassingID() {
        Intent intent = getIntent();
        postId = intent.getStringExtra("postId");
        userId = intent.getStringExtra("userId");
    }

    private void listViewSetUp(List<Post> posts) {
        PostApdapter adapter = new PostApdapter(this, posts);
        lsvSingleList.setAdapter(adapter);
    }

    private void addNewComment() {
        coreComment = edtSingleComment.getText().toString().trim();
        if(TextUtils.isEmpty(coreComment)) {
            edtSingleComment.setError("Advise filling the form");
        }

        Post temp = new Post(userId, originalPost.getId()
                , PostType.Answer, coreComment
                , 0, 0);

        edtSingleComment.setText("");
        database.createNewPost(temp);
        posts.add(temp);
        listViewSetUp(posts);
    }
}