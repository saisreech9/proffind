package com.example.proffind;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnect {

    private static final String ip = "172.16.32.198";
    private static final String port = "1433";
    private static final String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static final String database = "proffind";
    private static final String username = "sreee";
    private static final String password = "123456";
    private static String url = "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";" +
            "databasename="+ database+";user="+username+";password="+password+";";

    private Connection connection = null;

    /**
     * This class takes returns a {@link Connection} (session)
     * This method connect to the database via JDBC Java API Reference.
     * @return return {@link Connection} connection(session) to the database
     */
     private Connection connectToDatabase() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String connectionURL = null;
        try {
            Class.forName(Classes);
            connectionURL= url;
            return DriverManager.getConnection(connectionURL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void populateUserDetailsTable(int userId, String userName, String firstName,
                                         String lastName, String emailAddress, String password,
                                         String userType)
    {
        connection = connectToDatabase();
        if (connection!=null)
        {
            try (Statement stmt = connection.createStatement())
            {
                ResultSet rs = stmt.executeQuery(
                        "insert into UserDetails values("
                        +"'" + userId + "'" + ","
                        +"'" + userName + "'" + ","
                        +"'" + firstName + "'" + ","
                        +"'" + lastName + "'" + ","
                        +"'" + emailAddress + "'" + ","
                        +"'" + password + "'" + ","
                        +"'" + userType + "'" + ","
                        +"'10:00:00'" + ","
                        +"'10/18/2022'" + ","
                        +"'10:00:00'" + ","
                        +"'10/18/2022')");

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
