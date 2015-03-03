package com.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({"com.example.ldap.repositories"})
@EnableTransactionManagement
public class DatabaseConfiguration implements EnvironmentAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfiguration.class);

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment env) {
        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
    }

    @Bean(destroyMethod = "close")
    public HikariDataSource dataSource() {
        LOGGER.info("Configuring the datasource...");

        HikariConfig config = buildConfig();

        HikariDataSource dataSource = new HikariDataSource(config);

        dataSource.setAutoCommit(true);
        dataSource.setMinimumIdle(5);
        dataSource.setMaximumPoolSize(20);
        dataSource.setConnectionTimeout(10000);

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("com.example.entities.db");
        localSessionFactoryBean.setAnnotatedPackages("com.example.entities.db");

        return localSessionFactoryBean;
    }

    private HikariConfig buildConfig() {
        final HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(propertyResolver.getProperty("url"));
        hikariConfig.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
        hikariConfig.addDataSourceProperty("url", propertyResolver.getProperty("url"));
        hikariConfig.addDataSourceProperty("user", propertyResolver.getProperty("username"));
        hikariConfig.addDataSourceProperty("password", propertyResolver.getProperty("password"));

        return hikariConfig;
    }
}
