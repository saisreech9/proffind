package com.example.proffind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LoginPage extends AppCompatActivity {

    TextView signUp, enteredUserName;
    Button forgotPasswordPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        // Here we write all the logic for login page like email and password validation.

        signUp = (TextView) findViewById(R.id.signUpButton);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this,SignUp.class);
                startActivity(intent);
            }
        });

        forgotPasswordPage = (Button) findViewById(R.id.forgotPassword);
        forgotPasswordPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this,ForgotPassword.class);
                startActivity(intent);
            }
        });

        DatabaseConnect db = new DatabaseConnect();
        enteredUserName = findViewById(R.id.userName);
        String enteredUserNameDetails = enteredUserName.getText().toString();
        String dataBaseUserName = db.validateUserName(enteredUserNameDetails);
        if(dataBaseUserName.equals(enteredUserNameDetails))
        {
            //check for password
        }

    }
}