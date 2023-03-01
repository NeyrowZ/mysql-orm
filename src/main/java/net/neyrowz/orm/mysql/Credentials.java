package net.neyrowz.orm.mysql;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Credentials {

    private final String host;
    private final String database;
    private final String username;
    private final String password;

    public Credentials fromJson(File file) {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            BufferedReader bf = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bf.readLine()) != null) {
                sb.append(line);
            }
            bf.close();
            return new Gson().fromJson(sb.toString(), Credentials.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Credentials(String host, String database, String username, String password) {
        this.host = host;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}