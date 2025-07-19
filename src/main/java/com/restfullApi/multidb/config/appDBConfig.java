package com.restfullApi.multidb.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.*;

import javax.sql.DataSource;
import java.util.*;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "appdbEntityManagerFactory",
        transactionManagerRef = "appdbTransactionManager",
        basePackages = "com.restfullApi.multidb.repository.appdb"
)
public class appDBConfig {

    @Primary
    @Bean(name = "appdbDataSource")
    @ConfigurationProperties(prefix = "db.appdb")
    public DataSource appdbDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "appdbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean appdbEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("appdbDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.restfullApi.multidb.model.appdb")
                .persistenceUnit("appdb")
                .build();
    }

    @Primary
    @Bean(name = "appdbTransactionManager")
    public PlatformTransactionManager appdbTransactionManager(
            @Qualifier("appdbEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}