package com.example.studentdb;
import java.sql.*;

public class StudentDb {
    static Connection con;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/student_information_management_db";
    static String username = "root";
    static String password = "root";
    
    public static Connection getConnection() throws Exception{
        if(con == null){
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        }
        return con;
    }
}