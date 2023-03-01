package net.neyrowz.orm.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MysqlStatement {

    private final Mysql mysql;
    private final Map<Integer, Object> values;
    private final String sql;
    
    public MysqlStatement(String sql, Mysql mysql) {
        this.mysql = mysql;
        this.values = new HashMap<>();
        this.sql = sql;
    }
    
    public void addValue(String s) {
        this.values.put(this.values.size() + 1, s);
    }
    
    public void addValue(int i) {
        this.values.put(this.values.size() + 1, i);
    }
    
    public void addValue(boolean b) {
        this.values.put(this.values.size() + 1, b);
    }
    
    public PreparedStatement getStatement() throws SQLException {
        PreparedStatement statement = this.mysql.getConnection().prepareStatement(this.sql);
        for (int key : this.values.keySet()) {
            statement.setObject(key, this.values.get(key));
        }
        return statement;
    }
}
