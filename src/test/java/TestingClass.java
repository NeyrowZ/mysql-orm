import java.util.UUID;

public class TestingClass {

    private final String username;
    private final UUID uuid;
    private long createdAt;

    public TestingClass(String username, UUID uuid, long createdAt) {
        this.username = username;
        this.uuid = uuid;
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public UUID getUUID() {
        return uuid;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}