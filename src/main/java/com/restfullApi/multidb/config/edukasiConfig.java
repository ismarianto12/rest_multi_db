package com.restfullApi.multidb.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "edukasiEntityManagerFactory",
        transactionManagerRef = "edukasiTransactionManager",
        basePackages = "com.restfullApi.multidb.repository.edukasi"
)
public class edukasiConfig {

    @Bean(name = "edukasiDataSource")
    @ConfigurationProperties(prefix = "db.edukasi")
    public DataSource edukasiDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "edukasiEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean edukasiEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("edukasiDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.restfullApi.multidb.model.edukasi")
                .persistenceUnit("edukasi")
                .build();
    }

    @Bean(name = "edukasiTransactionManager")
    public PlatformTransactionManager edukasiTransactionManager(
            @Qualifier("edukasiEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}