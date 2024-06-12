package com.example.btl_nmh.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/btl_nmh";
    static final String USERNAME = "root";
    static final String PASSWORD = "20052002";

    public  Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

}
