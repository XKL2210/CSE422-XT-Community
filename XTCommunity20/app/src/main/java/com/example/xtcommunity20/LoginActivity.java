package com.example.xtcommunity20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import database.Database;

public class LoginActivity extends AppCompatActivity {
    private EditText edtLoginUsername, edtLoginPassword;
    private ImageView imvLogin;
    private String coreUsername, corePassword
            , coreID;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        constrainGUI();
        objectsInitialization();
        actionListener();
    }

    //Initialization
    private void constrainGUI() {
        edtLoginUsername = (EditText) findViewById(R.id.edtLoginUsername);
        edtLoginPassword = (EditText) findViewById(R.id.edtLoginPassword);
        imvLogin = (ImageView) findViewById(R.id.imvLogin);
    }

    private void objectsInitialization() {
        database = new Database();
    }

    private void actionListener() {
        imvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    //Functional Methods
    private void getCoreVariables() {
        coreUsername = edtLoginUsername.getText().toString().trim();
        corePassword = edtLoginPassword.getText().toString().trim();
    }

    private boolean editTextValidation() {
        boolean checkToken = true;
        if(TextUtils.isEmpty(coreUsername)) {
            edtLoginUsername.setError("Invalid Input: Advise filling the form");
            checkToken = false;
        }

        if(TextUtils.isEmpty(corePassword)) {
            edtLoginPassword.setError("Invalid Input: Advise filling the form");
            checkToken = false;
        }

        return checkToken;
    }

    private void doLogin() {
        getCoreVariables();

        if(!editTextValidation()) {
            return;
        }

        String successToken = database.loginToDatabase(coreUsername, corePassword);

        if(successToken.equalsIgnoreCase("0000")) {
            Toast.makeText(getApplicationContext(), "Error Code: 0000", Toast.LENGTH_SHORT).show();
        } else {
            coreID = successToken;
            toDashboard();
        }
    }

    private void toDashboard() {
        if(coreID.equalsIgnoreCase("4444")) {
            Toast.makeText(getApplicationContext(), "Admin Online", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        intent.putExtra("userId", coreID);
        startActivity(intent);
    }
}
