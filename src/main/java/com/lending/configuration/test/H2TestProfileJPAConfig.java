package com.lending.configuration.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.lending.repository"
})
@EnableTransactionManagement
public class H2TestProfileJPAConfig {

    @Bean
    @Profile("test")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //dataSource.setDriverClassName("org.hibernate.dialect.MySQL5Dialect");
        dataSource.setUrl("jdbc:mysql://localhost:3306/lending-test?createDatabaseIfNotExist=true");
        dataSource.setUsername("root");
        dataSource.setPassword("password");

        return dataSource;
    }

    // configure entityManagerFactory
    // configure transactionManager
    // configure additional Hibernate properties
}