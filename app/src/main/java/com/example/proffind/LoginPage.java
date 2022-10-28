package com.example.proffind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class LoginPage extends AppCompatActivity {

    TextView signUp, enteredUserName, enteredPassword;
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
    }
    public void confirmLogin(View v)
    {
        DatabaseConnect db = new DatabaseConnect();
        enteredUserName = findViewById(R.id.userName);
        String enteredUserNameDetails = enteredUserName.getText().toString();
        boolean dataBaseUserName = db.validateUserName(enteredUserNameDetails);
        if(dataBaseUserName)
        {
            //check for password
            enteredPassword = findViewById(R.id.password);
            String enteredPasswordDetails = enteredPassword.getText().toString();
            boolean isPasswordCorrect = db.validatePassword(enteredPasswordDetails);
            if(isPasswordCorrect)
            {
                Toast.makeText(LoginPage.this, "Good to go", Toast.LENGTH_SHORT).show();
                //login page logic
            }
            else
            {
                Toast.makeText(LoginPage.this, "Password is wrong", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(LoginPage.this, "UserName does not exist", Toast.LENGTH_SHORT).show();
        }
    }
}