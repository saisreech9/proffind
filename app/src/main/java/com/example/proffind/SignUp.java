package com.example.proffind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUp extends AppCompatActivity {

    ImageView backButton;
    Spinner userType;
    Button signUp;
    ArrayAdapter<CharSequence> adapter;
    TextView enteredUserName, enteredFirstName, enteredLastName, enteredEmailAddress,enteredPassword, reEnteredEmailAddress, reEnteredPassword ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        backButton = (ImageView) findViewById(R.id.backToLogin);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,LoginPage.class);
                startActivity(intent);
            }
        });

        userType = (Spinner) findViewById(R.id.userType);
        adapter = ArrayAdapter.createFromResource(this,R.array.usertypeselect, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        userType.setAdapter(adapter);
        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userTypeDetails,userNameDetails, firstNameDetails, lastNameDetails, emailAddressDetails, reEnteredemailAddressDetails, passwordDetails, reEnteredpasswordDetails;

                /**
                 * Getting the user entered details from application into local variables.
                 * */
                // getting the spinner to local variable
                //userType = findViewById(R.id.userType);
                enteredUserName = findViewById(R.id.signUpUserName);
                enteredFirstName = findViewById(R.id.firstName);
                enteredLastName = findViewById(R.id.lastName);
                enteredEmailAddress = findViewById(R.id.emailAddress);
                reEnteredEmailAddress = findViewById(R.id.newEmailAddress);
                enteredPassword = findViewById(R.id.password);
                reEnteredPassword = findViewById(R.id.newPassword);

                /**
                 * Assigning the values from applications to string literals
                 * */

                /**
                 * Validating username.
                 * Requirements:
                 * two accounts cannot have same username
                 * username cannot exceed 60 characters.
                 * username cannot have numbers or special characters.
                 * */
                userNameDetails = enteredUserName.getText().toString().toLowerCase();
                validations v = new validations();

                boolean validateUserName = v.nameValidations(userNameDetails);
                DatabaseConnect db = new DatabaseConnect();
                boolean checkUserNameExists = db.checkUserNameExists(userNameDetails);
                if(!checkUserNameExists)
                {
                    if (validateUserName) {
                        /**
                         * Validating firstname, lastname
                         * Requirements:
                         * name cannot be more than 60 characters.
                         * name cannot have numbers or special characters.
                         * */
                        firstNameDetails = enteredFirstName.getText().toString();
                        boolean validateFirstName = v.nameValidations(firstNameDetails);
                        if (validateFirstName) {
                            lastNameDetails = enteredLastName.getText().toString();
                            boolean validateLastName = v.nameValidations(lastNameDetails);
                            if (validateLastName) {
                                /**
                                 * Validating email address
                                 * Requirements:
                                 * email address should have domain included with '@' and a '.'.
                                 * */
                                emailAddressDetails = enteredEmailAddress.getText().toString().toLowerCase();
                                String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
                                boolean validateEmailAddress = emailAddressDetails.matches(regex);
                                if (validateEmailAddress) {
                                    reEnteredemailAddressDetails = reEnteredEmailAddress.getText().toString().toLowerCase();
                                    boolean validateNewEmailAddress = reEnteredemailAddressDetails.matches(regex);
                                    if (validateNewEmailAddress) {
                                        if (emailAddressDetails.equals(reEnteredemailAddressDetails)) {
                                            /**
                                             * Validating password
                                             * Requirements:
                                             * Password should have at least 1 upper case letter
                                             * Password should have at least 1 lower case letter
                                             * Password should have at least 1 number
                                             * Password should have at least 1 special character
                                             * Password length should vary from 8-20
                                             * */
                                            passwordDetails = enteredPassword.getText().toString();
                                            boolean validatePassword = v.passwordValidation(passwordDetails);
                                            if (validatePassword) {
                                                reEnteredpasswordDetails = reEnteredPassword.getText().toString();
                                                boolean validateNewPassword = v.passwordValidation(reEnteredpasswordDetails);
                                                if (validateNewPassword && passwordDetails.equals(reEnteredpasswordDetails)) {
                                                    String latestUserId = db.getLatestUserId();
                                                    int getLatestUserId = Integer.parseInt(latestUserId);
                                                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                                                    SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
                                                    Date date = new Date();
                                                    String getTodayDate = dateFormatter.format(date);
                                                    String getTodayTime = timeFormatter.format(date);
                                                    userTypeDetails = userType.getSelectedItem().toString();
                                                    db.populateUserDetailsTable(getLatestUserId+1,userNameDetails,firstNameDetails,
                                                            lastNameDetails,emailAddressDetails,passwordDetails,userTypeDetails,getTodayDate,getTodayTime);
                                                    Toast.makeText(SignUp.this, "Account Created", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(SignUp.this,LoginPage.class);
                                                    startActivity(intent);
                                                } else {
                                                    Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(SignUp.this, "Password does not match requirements", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(SignUp.this, "Both the email address should be same", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(SignUp.this, "Both the email address should be same", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(SignUp.this, "Email Id not valid", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(SignUp.this, "last name not valid", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignUp.this, "first name not valid", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUp.this, "user name not valid", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(SignUp.this,"User Name already exists",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}