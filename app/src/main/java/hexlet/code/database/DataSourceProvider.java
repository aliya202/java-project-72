package hexlet.code.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {
    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        String jdbcUrl = System.getenv("JDBC_DATABASE_URL");

        if (jdbcUrl != null && !jdbcUrl.isBlank()) {
            String host = System.getenv("HOST");
            String port = System.getenv("DB_PORT");
            String db = System.getenv("DATABASE");
            String username = System.getenv("USERNAME");
            String password = System.getenv("PASSWORD");

            jdbcUrl = "jdbc:postgresql://" + host + ":" + port + "/" + db
                    + "?user=" + username + "&password=" + password;

            config.setJdbcUrl(jdbcUrl);
            config.setDriverClassName("org.postgresql.Driver");
            if (username != null && !username.isBlank()) {
                config.setUsername(username);
            }
            if (password != null && !password.isBlank()) {
                config.setPassword(password);
            }
        } else {
            config.setJdbcUrl("jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false");
            config.setDriverClassName("org.h2.Driver");
        }

        config.setMaximumPoolSize(10);
        config.setPoolName("MyAppHikariPool");
        dataSource = new HikariDataSource(config);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}