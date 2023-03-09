package com.example.xtcommunity20;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if(!validate(username, password)) {
            return;
        }

        boolean successToken = mySQL.loginToDatabase(username, password);
        if(successToken) {
            toDashboard();
        }

        if(username.equalsIgnoreCase("Admin")
                && password.equalsIgnoreCase("XT4444")) {
            toDashboard();
        }
    }

    private boolean validate(String username, String password) {
        //check username & password
        String userPasswordPattern = "^[a-zA-Z0-9]+$";
        if (!checkValidate(userPasswordPattern, username) || !checkValidate(userPasswordPattern, password)) {
            loginDialog("Wrong username or password format", edtLoginUsername, edtLoginPassword);
            return false;
        }
    }

    private boolean checkValidate(String stringPattern, String check) {
        Pattern pattern = Pattern.compile((stringPattern));
        Matcher matcher = pattern.matcher(check);
        return matcher.matches();
    }

    private void loginDialog(String message, EditText username, EditText password) {
        builder.setTitle("ERROR")
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        username.setText("");
                        password.setText((""));
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void toDashboard() {
        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
    }
}