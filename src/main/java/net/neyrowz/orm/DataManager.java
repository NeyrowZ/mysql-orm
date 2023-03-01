package net.neyrowz.orm;

import com.google.gson.Gson;
import net.neyrowz.orm.mysql.Mysql;
import net.neyrowz.orm.mysql.MysqlStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DataManager<I, T> {

    private final String name;
    private final Mysql mysql;
    private final HashMap<I, T> datas;

    public DataManager(String name, Mysql mysql) {
        this.name = name;
        this.mysql = mysql;
        datas = new HashMap<>();
        this.setupTable();
    }

    public void insertInDB(I id, T obj) {
        MysqlStatement mysqlStatement = new MysqlStatement("INSERT INTO " + name + "(id, data) VALUES (?, ?)", mysql);
        mysqlStatement.addValue(getId(id));
        mysqlStatement.addValue(new Gson().toJson(obj));
        this.mysql.executeAsync(mysqlStatement);
    }

    public void updateInDB(I id, T obj, boolean async) {
        MysqlStatement mysqlStatement = new MysqlStatement("UPDATE " + name + " SET data = ? WHERE id = ?", mysql);
        mysqlStatement.addValue(new Gson().toJson(obj));
        mysqlStatement.addValue(getId(id));
        if (async) {
            this.mysql.executeAsync(mysqlStatement);
        }
        else {
            this.mysql.execute(mysqlStatement);
        }
    }

    public T getInDB(I id, Class<T> clazz) throws SQLException {
        MysqlStatement mysqlStatement = new MysqlStatement("SELECT * FROM " + name + " WHERE id = ?", mysql);
        mysqlStatement.addValue(getId(id));
        ResultSet result = this.mysql.executeGet(mysqlStatement);
        if (result == null) {
            return null;
        }
        T t = new Gson().fromJson(result.getString("data"), clazz);
        result.getStatement().close();
        return t;
    }

    public HashMap<I, T> getDatas() {
        return datas;
    }

    public String getId(I id) {
        return id.toString();
    }

    public void shutdown() {
        getDatas().forEach((i, t) -> {
            updateInDB(i, t, false);
        });
    }

    public void setupTable() {
        this.mysql.execute("CREATE TABLE IF NOT EXISTS " + name + "(id TEXT, data TEXT)");
    }
}