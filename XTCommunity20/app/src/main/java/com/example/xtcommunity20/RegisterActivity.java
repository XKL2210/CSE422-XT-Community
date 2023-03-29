package com.example.xtcommunity20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import database.Database;
import model.Gender;
import model.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtRegisterUsername, edtRegisterPassword
            , edtRegisterFullName, edtRegisterEmail
            , edtYearsExperience, edtRegisterContactNumber;
    private RadioButton rdbRegisterMale, rdbRegisterFemale;
    private ImageView imvRegister;
    private String coreUsername, corePassword
            , coreFullName, coreEmail
            , coreBirthday, coreContactNumber;
    private Gender coreGender;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        constrainGUI();
        actionListener();
    }

    //Initialization
    private void constrainGUI() {
        edtRegisterUsername = (EditText) findViewById(R.id.edtRegisterUsername);
        edtRegisterPassword = (EditText) findViewById(R.id.edtRegisterPassword);
        edtRegisterFullName = (EditText) findViewById(R.id.edtRegisterName);
        edtRegisterEmail = (EditText) findViewById(R.id.edtRegisterEmail);
        edtRegisterContactNumber = (EditText) findViewById(R.id.edtRegisterPhone);
        edtYearsExperience = (EditText) findViewById(R.id.edtYearsExperience);
        rdbRegisterFemale = (RadioButton) findViewById(R.id.rdbRegisterFemale);
        rdbRegisterMale = (RadioButton) findViewById(R.id.rdbRegisterMale);
        imvRegister = (ImageView) findViewById(R.id.imvRegister);
    }

    private void objectsInitialization() {
        database = new Database();
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
    private void getCoreVariables() {
        coreUsername = edtRegisterUsername.getText().toString().trim();
        corePassword = edtRegisterPassword.getText().toString().trim();
        coreFullName = edtRegisterEmail.getText().toString().trim();
        coreEmail = edtRegisterEmail.getText().toString().trim();
        coreContactNumber = edtRegisterContactNumber.getText().toString().trim();
        coreBirthday = edtYearsExperience.getText().toString().trim();
        if(rdbRegisterFemale.isChecked()) {
            coreGender = Gender.Female;
        } else {
            coreGender = Gender.Male;
        }
    }

    private boolean editTextValidation() {
        boolean checkToken = true;
        if(TextUtils.isEmpty(coreUsername)) {
            edtRegisterUsername.setError("Invalid Input: Advise filling the form");
            checkToken = false;
        }

        if(TextUtils.isEmpty(corePassword)) {
            edtRegisterPassword.setError("Invalid Input: Advise filling the form");
            checkToken = false;
        }

        if(TextUtils.isEmpty(coreEmail)) {
            edtRegisterEmail.setError("Invalid Input: Advise filling the form");
            checkToken = false;
        }

        if(TextUtils.isEmpty(coreContactNumber)) {
            edtRegisterContactNumber.setError("Invalid Input: Advise filling the form");
            checkToken = false;
        }

        if(TextUtils.isEmpty(coreFullName)) {
            edtRegisterFullName.setError("Invalid Input: Advise filling the form");
            checkToken = false;
        }

        if(TextUtils.isEmpty(coreBirthday)) {
            edtYearsExperience.setError("Invalid Input: Advise filling the form");
            checkToken = false;
        }

        return checkToken;
    }

    private void doRegister() {
        getCoreVariables();

        if(!editTextValidation()) {return;};

        User tempUser = new User(coreUsername, corePassword
                , coreFullName, coreGender
                , coreBirthday, coreEmail
                , coreContactNumber);

        String successToken = database.registerToDatabase(tempUser);

        if(successToken.equalsIgnoreCase("0000")) {
            Toast.makeText(getApplicationContext(), "Error code: 0000", Toast.LENGTH_SHORT).show();
            return;
        } else {
            finish();
        }
    }
}
