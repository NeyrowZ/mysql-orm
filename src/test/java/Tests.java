import java.sql.SQLException;
import java.util.UUID;

public class Tests {

    private static PlayerDataManager pdm;

    public static void main(String[] args) throws SQLException {
        pdm = new PlayerDataManager();
        insert("dbe3855b-c05b-4243-a79a-2b635264b71e");
        System.out.println(get(UUID.fromString("dbe3855b-c05b-4243-a79a-2b635264b71e")).getUsername());
    }

    public static void insert(String id) throws SQLException {
        UUID uuid = UUID.fromString(id);
        if (get(uuid) != null) return;
        pdm.insertInDB(uuid, new TestingClass("NeyrowZ", uuid, System.currentTimeMillis()));
    }

    public static TestingClass get(UUID uuid) throws SQLException {
        return pdm.getInDB(uuid, TestingClass.class);
    }
}