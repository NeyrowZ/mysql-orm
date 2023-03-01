import net.neyrowz.orm.DataManager;
import net.neyrowz.orm.mysql.Credentials;
import net.neyrowz.orm.mysql.Mysql;

import java.util.UUID;

public class PlayerDataManager extends DataManager<UUID, TestingClass> {

    public PlayerDataManager() {
        super("players", new Mysql(new Credentials("localhost:3306", "orm", "root", "")));
    }

    @Override
    public String getId(UUID id) {
        return id.toString();
    }
}