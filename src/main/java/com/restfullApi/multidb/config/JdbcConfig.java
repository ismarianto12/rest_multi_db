package com.restfullApi.multidb.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    @Bean(name = "edukasiJdbcTemplate")
    public JdbcTemplate edukasiJdbcTemplate(@Qualifier("edukasiDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}