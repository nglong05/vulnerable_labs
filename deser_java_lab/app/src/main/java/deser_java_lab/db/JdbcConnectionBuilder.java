package deser_java_lab.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;

public record JdbcConnectionBuilder (String driver, String scheme, String host, int port, String database, String user, String pass, boolean autocommit) {
    
    public static JdbcConnectionBuilder from(String driver, String scheme, String host, int port, String database, String user, String pass) {
        return new JdbcConnectionBuilder(driver, scheme, host, port, database, user, pass, false);
    }

    public JdbcConnectionBuilder withAutoCommit() {
        return new JdbcConnectionBuilder(driver, scheme, host, port, database, user, pass, true);
    }

    public Connection connect(int timeoutSeconds) throws SQLException {
        try {
            Class.forName(driver);
        }
        catch (ClassNotFoundException ignored) {}

        DriverManager.setLoginTimeout(timeoutSeconds);
        var url = String.format("jdbc:%s://%s:%d/%s", scheme, host, port, database);
        var c = DriverManager.getConnection(url, user, pass);
        c.setAutoCommit(autocommit);
        return c;
    }
} 
