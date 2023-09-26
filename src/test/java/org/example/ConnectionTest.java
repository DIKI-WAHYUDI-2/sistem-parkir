package org.example;

import ConnectionDataBase.ConnectionDB;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {

    @Test
    void hikariCPTest() throws SQLException {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/belajar");
        config.setUsername("root");
        config.setPassword("root");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(60 * 60 * 60_000);

        HikariDataSource dataSource = new HikariDataSource(config);

        Connection connection = dataSource.getConnection();

        connection.close();
        dataSource.close();
    }

    @Test
    void connectionDBTest() throws SQLException{
        Connection connection = ConnectionDB.getDataSource().getConnection();
    }
}
