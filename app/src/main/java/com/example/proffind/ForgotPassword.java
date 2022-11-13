package com.example.proffind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForgotPassword extends AppCompatActivity {

    ImageView backButton;
    Button forgotPasswordSubmit;
    EditText forgotPasswordUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        backButton = (ImageView) findViewById(R.id.forgotPasswordBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPassword.this, LoginPage.class);
                startActivity(intent);
                //get the email address from database with the username
                //send password details to email if email address found.
                //if username does not exists, throw error message

            }
        });

        forgotPasswordSubmit = (Button) findViewById(R.id.forgotPasswordSubmit);
        forgotPasswordSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ForgotPassword.this,LoginPage.class);
//                startActivity(intent);
                forgotPasswordUserName = findViewById(R.id.forgotPasswordUserName);
                String enteredUserName = forgotPasswordUserName.getText().toString();
                saveLoginDetails.getInstance().setDetails(enteredUserName);
                DatabaseConnect db = new DatabaseConnect();
                boolean checkUserName = db.checkUserNameExists(enteredUserName);
                if(checkUserName)
                {

                }
                else
                {
                    Toast.makeText(ForgotPassword.this, "user Name does not exists", Toast.LENGTH_SHORT).show();
                }
//                if(saveLoginDetails.getInstance().getEmailAddress().equals("null"))
//                {
//                    Toast.makeText(ForgotPassword.this, "User Name does not exists", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    String emailAddress = saveLoginDetails.getInstance().getEmailAddress();
//                    System.out.println(emailAddress);
//                }
            }
        });
    }
    public void sendPasswordEmail(String userEmailAddress, String passwordMessage)
    {
        String senderEmail = "testcis634@gmail.com";
        //String recieverEmailStudent = saveLoginDetails.getInstance().getEmailAddress();
        String senderPassword = "ztjjbsieyumsqpjn";

        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth","true");

        javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(userEmailAddress));
            mimeMessage.setSubject("Confirmation Email");
            mimeMessage.setText(passwordMessage);

            Thread thread =  new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}