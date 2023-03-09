package com.example.xtcommunity20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imvMainDashboard, imvMainLogin, imvMainSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constrainsGUI();
        actionListener();
    }
    //Initialization
    private void constrainsGUI() {
        imvMainDashboard = (ImageView) findViewById(R.id.imvMainDashboard);
        imvMainLogin = (ImageView) findViewById(R.id.imvMainLogin);
        imvMainSignUp = (ImageView) findViewById(R.id.imvMainSignUp);
    }

    private void actionListener() {
        imvMainDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDashboardActivity();
            }
        });

        imvMainSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSignUp();
            }
        });

        imvMainLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin();
            }
        });
    }
    //Functional Methods
    private void toDashboardActivity() {
        startActivity(new Intent(MainActivity.this, DashboardActivity.class));
    }

    private void toSignUp() {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

    private void toLogin() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}