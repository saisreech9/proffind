package com.example.proffind;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validations {
    Pattern specialCharacters = Pattern.compile(
            "[@!#$%^=&*()_+{}:,./;~<>?^0123456789]", Pattern.CASE_INSENSITIVE);

    //Pattern passwordRegex = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$");
    Pattern passwordRegex = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=()])(?=\\S+$).{8,20}$");
    public boolean nameValidations(String userName) {
        Matcher m = specialCharacters.matcher(userName);
        boolean match = m.find();
        if (userName.length() > 60 || match) {
            return false;
        } else {
            return true;
        }
    }

    public boolean passwordValidation(String password)
    {
        Matcher m = passwordRegex.matcher(password);
        boolean match = m.find();
        if(match)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
