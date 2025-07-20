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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "appEntityManagerFactory",
        transactionManagerRef = "appdbTransactionManager",
        basePackages = "com.restFullApi.multidb.respository.appdb"
)

public class sekolahDbConfig {
    @Primary
    @Bean(name = "sekolahDataSource")
    @ConfigurationProperties(prefix = "db.sekolah")
    public DataSource sekolahDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();

    }

    @Primary
    @Bean(name = "appEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sekolahEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("sekolahDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.restFullApi.multidb.model").persistenceUnit("sekolah").build();

    }

    @Primary
    @Bean(name = "appEntityManager")
    public PlatformTransactionManager appdbTransactionManager(
            @Qualifier("appEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
