package br.com.PetRepete.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Conexao {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=PetRepete;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "Thiago";
    private static final String PASSWORD = "1709";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static PreparedStatement prepareStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }
}


