/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.*;
import java.util.Properties;


/**
 *
 * @author Han
 */
public abstract class RDBImplCommand {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/advising";

    //  Database credentials
    static final String USER = "root";
    
    static final String PASS = "ashay";

    protected Object result;
    protected Connection conn;
    protected PreparedStatement statement;
    protected ResultSet resultSet;
    protected boolean userFlag=true;

    public void execute() {
        try {
            connectDB();
            queryDB();
            disconnectDB();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            disconnectDB();
        }
    }

    protected void connectDB() throws Exception {
        System.out.println("Connecting MySQL DB");
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("connection"+conn);
        //conn = DriverManager.getConnection("jdbc:derby://localhost:1527/advising;create=true;user=advising;password=advising");
    }

    protected void disconnectDB() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public Object getResult() {
        return result;
    }

    public abstract void queryDB() throws SQLException;

    public abstract void processResult();
}
