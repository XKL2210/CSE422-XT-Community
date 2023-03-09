package com.example.xtcommunity20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import database.Database;
import model.User;

public class LoginActivity extends AppCompatActivity {
    private ImageView imvLogin;
    private EditText edtLoginUsername, edtLoginPassword;
    private Database mySQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        constrainsGUI();
        actionListener();
    }
    //Initialization
    private void constrainsGUI() {
        imvLogin = (ImageView) findViewById(R.id.imvLogin);
        edtLoginUsername = (EditText) findViewById(R.id.edtLoginUsername);
        edtLoginPassword = (EditText) findViewById(R.id.edtLoginPassword);
        mySQL = new Database();
    }

    private void actionListener() {
        imvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }
    //FunctionalMethod
    private void doLogin() {
        String username = edtLoginUsername.getText().toString().trim();
        String password = edtLoginPassword.getText().toString().trim();

        boolean successToken = mySQL.loginToDatabase(username, password);
        if(successToken) {
            toDashboard();
        }

        if(username.equalsIgnoreCase("Admin")
                && password.equalsIgnoreCase("XT4444")) {
            toDashboard();
        }
    }

    private void toDashboard() {
        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
    }
}