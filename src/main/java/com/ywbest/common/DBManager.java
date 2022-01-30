package com.ywbest.common;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class DBManager {
    public static  final String DB_URL = "jdbc:h2:file:D:/Users/ywbest/H2Datas";
    public static  final String USER = "sa";
    public static  final String PASSWORD = "";
    private Connection connection;
    private Statement statement;

    public DBManager() throws SQLException{
        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        statement = connection.createStatement();
    }

    public DBManager(String dbUrl, String user, String password) throws SQLException{
        connection = DriverManager.getConnection(dbUrl, user, password);
        statement = connection.createStatement();
    }

    public int excuteUpdate(String sql){
        int count = 0;
        try{
            count = statement.executeUpdate(sql);
            log.info(count+" row inserted.");
        }catch (SQLException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return count;
    }

    public boolean exist(String sql){
        boolean result = false;
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            result = resultSet.next();
            log.info("isExist : "+result);
        }catch (SQLException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return result;
    }

    public void  close(){
        try {
            if(statement != null && !statement.isClosed()){
                statement.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public Statement getStatement(){
        return statement;
    }
}
