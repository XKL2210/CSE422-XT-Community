package com.example.xtcommunity20;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import database.Database;
import model.Gender;
import model.User;

public class SignUpActivity extends AppCompatActivity {
    private ImageView imvRegister;
    private EditText edtRegisterUsername, edtRegisterPassword, edtRegisterName
            , edtRegisterEmail, edtRegisterBirthday, edtRegisterMobile;
    private RadioButton rdbRegisterMale, rdbRegisterFemale;
    private Database mySQL;

    private AlertDialog.Builder builder = new AlertDialog.Builder(this);



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

        if(!validate(username, password, name, email, mobile, birthday)) {
            return;
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

    private boolean validate(String username, String password, String name, String email
            , String mobile, String birthday) {
        //check username & password
        String userPasswordPattern =  "^[a-zA-Z0-9]+$";
        if(!checkValidate(userPasswordPattern, username)||!checkValidate(userPasswordPattern, password)) {
            registerDialog("Wrong username or password format", edtRegisterPassword);
            return false;
        }

        //check name
        String namePattern = "^[a-zA-Z\\s]+$";
        if(!checkValidate(namePattern, name)) {
            registerDialog("Wrong name format", edtRegisterName);
            return false;
        }

        //check email
        String mailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if(!checkValidate(mailPattern, email)) {
            registerDialog("Wrong mail format", edtRegisterEmail);
            return false;
        }

        //check mobile
        String mobilePattern = "^\\+?[0-9]{10,13}$";
        if(!checkValidate(mobilePattern, mobile)) {
            registerDialog("Wrong phone number format", edtRegisterMobile);
            return false;
        }

        //check birthday
        if(Integer.parseInt(birthday)>2023&&Integer.parseInt(birthday)<1990) {
            registerDialog("Wrong birthday", edtRegisterBirthday);
            return false;
        }

        return true;
    }

    private boolean checkValidate(String stringPattern, String check) {
        Pattern pattern = Pattern.compile((stringPattern));
        Matcher matcher = pattern.matcher(check);
        return matcher.matches();
    }

    private void registerDialog(String message, EditText edtText) {
        builder.setTitle("ERROR")
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edtText.setText("");
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}