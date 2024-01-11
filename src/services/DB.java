package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private  String url = "jdbc:mysql://localhost:3306/school";
    private  String username = "root";
    private  String password = "";
    private  Connection connection;

    public DB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection created.");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection() {
        return connection;
    }
    public void disconnect(){
        try{
            connection.close();
            System.out.println("Connection to db is closed. ");

        }catch(Exception e){
            System.out.println("Database closure issue!");
        }
    }

}
