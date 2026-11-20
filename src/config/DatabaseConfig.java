package config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public record DatabaseConfig(String url, String user, String password) {

    private static final Path FILE = Path.of("db.properties");

    public static DatabaseConfig load() throws IOException {
        if (Files.notExists(FILE)) {
            return new DatabaseConfig("", "", "");
        }
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(FILE)) {
            props.load(in);
        }
        return new DatabaseConfig(
                props.getProperty("db.url", ""),
                props.getProperty("db.user", ""),
                props.getProperty("db.password", ""));
    }

    public void save() throws IOException {
        Properties props = new Properties();
        props.setProperty("db.url", url);
        props.setProperty("db.user", user);
        props.setProperty("db.password", password);
        try (OutputStream out = Files.newOutputStream(FILE)) {
            props.store(out, null);
        }
    }

    @Override
    public String toString() {
        return "DatabaseConfig[url=" + url + ", user=" + user + ", password=***]";
    }
}
