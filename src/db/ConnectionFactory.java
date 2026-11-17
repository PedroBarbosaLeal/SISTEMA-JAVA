package db;

import config.DatabaseConfig;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        DatabaseConfig config;
        try {
            config = DatabaseConfig.load();
        } catch (IOException e) {
            throw new SQLException("Não foi possível ler db.properties", e);
        }
        return DriverManager.getConnection(config.url(), config.user(), config.password());
    }
}
