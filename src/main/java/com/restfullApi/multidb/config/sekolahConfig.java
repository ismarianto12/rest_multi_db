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
        entityManagerFactoryRef = "sekolahEntityManagerFactory",
        transactionManagerRef = "sekolahTransactionManager",
        basePackages = "com.restfullApi.multidb.repository.sekolah"
)
public class sekolahConfig {

    @Bean(name = "sekolahDataSource")
    @ConfigurationProperties(prefix = "db.sekolah")
    public DataSource sekolahDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "sekolahEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sekolahEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("sekolahDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.restfullApi.multidb.model.sekolah")
                .persistenceUnit("sekolah")
                .build();
    }

    @Bean(name = "sekolahTransactionManager")
    public PlatformTransactionManager sekolahTransactionManager(
            @Qualifier("sekolahEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}