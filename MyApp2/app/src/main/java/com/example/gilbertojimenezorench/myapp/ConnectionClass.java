package com.example.gilbertojimenezorench.myapp;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by gilbertojimenezorench on 10/14/16.
 */

public class ConnectionClass {

    String ip = "mysqldb.capscbgbnhob.us-west-2.rds.amazonaws.com:3306";
    String classs = "net.sourceforge.jtds.jdbc.Driver";
    String db = "mysqldb";
    String un = "postgres";
    String password = "password0987";

    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://dbserver.capscbgbnhob.us-west-2.rds.amazonaws.com:5432/",
                            un, password);

            //rs.close();
            //stmt.close();
            //c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

        }
        return c;
    }

}