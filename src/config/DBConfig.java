package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConfig {
    private Properties props = new Properties();

    public DBConfig() {
        try (FileInputStream fis = new FileInputStream("config/db.properties")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return props.getProperty("db.url");
    }

    public String getUsername() {
        return props.getProperty("db.username");
    }

    public String getPassword() {
        return props.getProperty("db.password");
    }
}

