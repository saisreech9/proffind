package com.example.proffind;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class saveLoginDetails {

    String firstName;
    String lastName;
    String emailAddress;
    String userType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    String userName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    private static saveLoginDetails saveLoginDetailsInstance = null;
    public static saveLoginDetails getInstance() {
        if (saveLoginDetailsInstance == null) {
            saveLoginDetailsInstance = new saveLoginDetails();
        }
        return saveLoginDetailsInstance;
    }


        public void setDetails (String userName)
        {
            DatabaseConnect db = new DatabaseConnect();
            Connection conn = db.connectToDatabase();
            if (conn != null) {
                try {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from UserDetails" +
                            " where userName = " + "'" + userName + "'");
                    while (rs.next()) {
                        setFirstName(rs.getString("firstName"));
                        setLastName(rs.getString("lastName"));
                        setEmailAddress(rs.getString("emailAddress"));
                        setUserType(rs.getString("userType"));
                        setUserName(rs.getString("userName"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

