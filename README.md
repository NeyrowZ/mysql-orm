<img src="https://cdn.discordapp.com/attachments/902223703385993216/1080541563773521990/NeyrowZ2.png" align="left" height="240px" alt="ArcadeLabs">
<div align="center">

<br>

# **Mysql-ORM Project**

![GitHub downloads](https://img.shields.io/github/downloads/neyrowz/mysql-orm/total?color=ffd000&style=for-the-badge)
![GitHub issues](https://img.shields.io/github/issues/NeyrowZ/mysql-orm?color=ffd000&style=for-the-badge)<br>
![Status](https://img.shields.io/badge/STATUS-BETA-3a0ca3?color=ffd000&style=for-the-badge)
![CodeFactor Grade](https://www.codefactor.io/repository/github/neyrowz/mysql-orm/badge?style=for-the-badge)

</div>
<br>

## Usage

> ### **Gradle**
```groovy
repositories {
    mavenCentral()
}

dependencies {
    compile 'net.neyrowz.orm:mysql-orm:1.0.0'
}
```

> ### **Maven**
```xml
<dependency>
    <groupId>net.neyrowz.orm</groupId>
    <artifactId>mysql-orm</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Snippets

- **Host:** `localhost:3306`
- **Database:** `dev`
- **Username:** `neyrowz`
- **Password:** `12345six`

```java
public class PlayerDataManager extends DataManager<UUID, PlayerData> {

    public PlayerDataManager() {
        super("playerData", new Mysql(new Credentials("localhost:3306", "dev", "neyrowz", "")));
    }

    @Override
    public String getId(UUID id) {
        return id.toString(); // Allow you to format the string key saved in the database.
    }
}
```

**Note**
You can also use credentials from a json file.

```java
new Credentials("localhost:3306", "dev", "neyrowz", "")
                    .fromJson(new File(".../path/credentials.json"));
```

```json
{
  "host": "localhost:3306",
  "database": "dev",
  "username": "neyrowz",
  "password": "12345six"
}
```
