package com.example.proffind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ForgotPassword extends AppCompatActivity {

    ImageView backButton;
    Button forgotPasswordSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        backButton = (ImageView) findViewById(R.id.forgotPasswordBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPassword.this,LoginPage.class);
                startActivity(intent);
            }
        });

        forgotPasswordSubmit = (Button) findViewById(R.id.forgotPasswordSubmit);
        forgotPasswordSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPassword.this,LoginPage.class);
                startActivity(intent);
            }
        });
    }
}