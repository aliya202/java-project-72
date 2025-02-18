package hexlet.code.repository;

import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.database.DataSourceProvider;

public class BaseRepository {
    public static HikariDataSource dataSource = (HikariDataSource) DataSourceProvider.getDataSource();
}
