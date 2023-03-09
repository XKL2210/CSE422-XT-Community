package com.example.xtcommunity20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.UUID;

import database.Database;
import model.Gender;
import model.User;

public class SignUpActivity extends AppCompatActivity {
    private ImageView imvRegister;
    private EditText edtRegisterUsername, edtRegisterPassword, edtRegisterName
            , edtRegisterEmail, edtRegisterBirthday, edtRegisterMobile;
    private RadioButton rdbRegisterMale, rdbRegisterFemale;
    private Database mySQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        constrainsGUI();
        actionListener();
    }
    //Initialization
    private void constrainsGUI() {
        imvRegister = (ImageView) findViewById(R.id.imvRegister);
        edtRegisterUsername = (EditText) findViewById(R.id.edtRegisterUsername);
        edtRegisterPassword = (EditText) findViewById(R.id.edtRegisterPassword);
        edtRegisterEmail = (EditText) findViewById(R.id.edtRegisterEmail);
        edtRegisterName = (EditText) findViewById(R.id.edtRegisterName);
        edtRegisterBirthday = (EditText) findViewById(R.id.edtRegisterBirthday);
        edtRegisterMobile = (EditText) findViewById(R.id.edtRegisterPhone);
        rdbRegisterFemale = (RadioButton) findViewById(R.id.rdbRegisterFemale);
        rdbRegisterMale = (RadioButton) findViewById(R.id.rdbRegisterMale);
        mySQL = new Database();
    }

    private void actionListener() {
        imvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegister();
            }
        });
    }
    //Functional Methods
    private void doRegister() {
        String username = edtRegisterUsername.getText().toString().trim();
        String password = edtRegisterPassword.getText().toString().trim();
        String name = edtRegisterName.getText().toString().trim();
        String email = edtRegisterEmail.getText().toString().trim();
        String mobile = edtRegisterMobile.getText().toString().trim();
        String birthday = edtRegisterBirthday.getText().toString().trim();
        Gender gender;
        if(rdbRegisterMale.isChecked()) {
            gender = Gender.Male;
        } else {
            gender = Gender.Female;
        }

        User user = new User(username, password);
        user.setEmail(email);
        user.setGender(gender);
        user.setPhone(mobile);
        user.setFullName(name);
        user.setDateOfBirth(birthday);

        mySQL.uploadNewUserToDatabase(user);

        finish();
    }
}