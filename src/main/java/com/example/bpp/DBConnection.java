package com.example.bpp;

import com.example.bpp.algorithms.Bin;

import java.sql.*;
import java.util.List;

public class DBConnection {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bpp";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void sendInserts(List<Bin> bins,int executionTime) throws Exception {
        Connection conn = DBConnection.getConnection();
        if(conn == null) throw new Exception("Brak połączenia z bazą danych");
        PreparedStatement stm;

        String sql = "INSERT INTO saves (executionTime) VALUES (?)";
        stm = conn.prepareStatement(sql);
        stm.setInt(1, executionTime);
        stm.executeUpdate();

        ResultSet rs = stm.executeQuery("SELECT id FROM saves ORDER BY id DESC LIMIT 1");
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("id");
        }

        for (Bin bin : bins) {
            String sql2 = "INSERT INTO data (usedSpace, capacity, saves_id) VALUES (?,?,?)";
            stm = conn.prepareStatement(sql2);
            stm.setInt(1, bin.used);
            stm.setInt(2, bin.capacity);
            stm.setInt(3, id);
            stm.executeUpdate();
        }
        stm.close();
    }
}
