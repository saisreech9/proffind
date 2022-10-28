package com.example.proffind;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.transform.Result;

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

    public String getLatestUserId()
    {
        connection = connectToDatabase();
        if(connection!=null)
        {
            try(Statement stmt = connection.createStatement())
            {
                ResultSet rs = stmt.executeQuery("SELECT userId FROM UserDetails order by userId desc");
                while(rs.next())
                {
                    return rs.getString(1);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
    public void populateUserDetailsTable(int userId, String userName, String firstName,
                                         String lastName, String emailAddress, String password,
                                         String userType, String currentDate, String currentTime)
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
                        +"'" + currentTime + "'" + ","
                        +"'" + currentDate + "'" + ","
                        +"'" + currentTime + "'" + ","
                        +"'" + currentDate + "'" + ")");

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public boolean validateUserName(String enteredUserName)
    {
        connection = connectToDatabase();
        try(Statement stmt = connection.createStatement())
        {
            ResultSet rs = stmt.executeQuery("select userName from UserDetails where " +
                    "userName = " + "'" + enteredUserName + "'");
            while(rs.next())
            {
                if(rs.getString(1).equals(enteredUserName))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validatePassword(String password)
    {
        connection = connectToDatabase();
        try(Statement stmt = connection.createStatement())
        {
            ResultSet rs = stmt.executeQuery("select password from UserDetails where " +
                    "password = " + "'" + password + "'");
            while(rs.next())
            {
                if(password.equals(rs.getString(1)))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

}
