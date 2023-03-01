package net.neyrowz.orm.mysql;

import java.sql.*;

public class Mysql {

    private final Credentials credentials;
    private Connection connection;

    public Mysql(Credentials credentials) {
        this.credentials = credentials;
    }

    public Connection getConnection() {
        if (this.connection == null) {
            try {
                this.connection = DriverManager.getConnection("jdbc:mysql://" + credentials.getHost() + "/" + credentials.getDatabase() + "?characterEncoding=utf8", credentials.getUsername(), credentials.getPassword());
                System.out.println("[Mysql] Successfully connected.");
            } catch (SQLException err) {
                System.out.println("[Mysql] Unable to connect to " + credentials.getHost() + ".");
                err.printStackTrace();
            }
        }
        return this.connection;
    }

    public void execute(MysqlStatement req) {
        try {
            PreparedStatement statement = req.getStatement();
            statement.executeUpdate();
            statement.close();
        } catch (SQLException err) {
            err.printStackTrace();
        }

    }

    public void executeAsync(MysqlStatement req) {
        new Thread(() -> {
            try {
                PreparedStatement statement = req.getStatement();
                statement.executeUpdate();
                statement.close();
            } catch (SQLException err) {
                err.printStackTrace();
            }
        }).start();
    }

    public void execute(String sql) {
        try {
            Statement statement = this.getConnection().createStatement();
            statement.execute(sql);
            statement.close();
        } catch (SQLException err) {
            err.printStackTrace();
        }

    }

    public void executeAsync(String sql) {
        new Thread(() -> {
            try {
                Statement statement = connection.createStatement();
                statement.execute(sql);
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public ResultSet executeGet(MysqlStatement req) {
        try {
            PreparedStatement statement = req.getStatement();
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                return results;
            } else {
                statement.close();
                return null;
            }
        } catch (SQLException err) {
            err.printStackTrace();
            return null;
        }
    }
}
