package Dao;
import javax.swing.*;
import java.sql.*;

public class Jdbc {
    public static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    public static final String DB_URL = "jdbc:mariadb://localhost:3306/RJGC";
    public static final String USER = "root";
    public static final String PWD = "10987654321";
    public static Connection CONNECTION = null;

    public static void initDB(){
        Statement statement = null;
        try {
            //×¢²ájdbcÇý¶¯
            Class.forName(JDBC_DRIVER);
            CONNECTION = DriverManager.getConnection(DB_URL,USER,PWD);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public static ResultSet SelectById(String tableName,String IdName,String Id){
        Connection connection = CONNECTION;
        try {
            String Sql = "SELECT * FROM "+tableName+" WHERE "+IdName+" = "+Id+" ;";
            Statement statement = connection.createStatement();
            return statement.executeQuery(Sql);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
