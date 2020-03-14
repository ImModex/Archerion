package de.Modex.bungee.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

    private static String username;
    private static String password;
    private static String database;
    private static String host;
    private static String port;
    private static Connection con;

    static {
        username = Config.mysqlConfig.getString("username");
        password = Config.mysqlConfig.getString("password");
        database = Config.mysqlConfig.getString("database");
        host = Config.mysqlConfig.getString("host");
        port = Config.mysqlConfig.getString("port");
    }

    public static boolean connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                System.out.println(Data.prefix + "§7MySQL connection §aestablished!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public static void close() {
        if (isConnected()) {
            try {
                con.close();
                System.out.println(Data.prefix + "§7MySQL connection §cclosed!");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static boolean isConnected() {
        return con != null;
    }

    public static void createDefaultTables() {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS banned (uuid VARCHAR(100) PRIMARY KEY NOT NULL, end_date VARCHAR(100) NOT NULL, reason VARCHAR(300), banned_by VARCHAR(100) NOT NULL)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void update(String query) {
        if(isConnected()) {
            try {
                con.createStatement().executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet getResult(String query) {
        if(isConnected()) {
            try {
                return con.createStatement().executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
