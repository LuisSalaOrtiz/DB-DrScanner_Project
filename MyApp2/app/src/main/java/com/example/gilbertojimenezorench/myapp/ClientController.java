package com.example.gilbertojimenezorench.myapp;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClientController {

    public static ResultSet select(String user, String pass, String sqlCommand){
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {


            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://dbserver.capscbgbnhob.us-west-2.rds.amazonaws.com:5432/",
                            user, pass);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            rs = stmt.executeQuery( sqlCommand);
            return rs;
            //rs.close();
            //stmt.close();
            //c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            return rs;
        }
        //System.out.println("Operation done successfully");
        //return rs;
    }

    public static boolean sqlCommand(String user, String pass, String sqlCommand){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://dbserver.capscbgbnhob.us-west-2.rds.amazonaws.com:5432/",
                            user, pass);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = sqlCommand;
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            return false;
        }
        System.out.println("Operation done successfully");
        return true;
    }


}