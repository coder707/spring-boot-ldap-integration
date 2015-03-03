package com.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment env) {
        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
    }

    @Bean(destroyMethod = "close")
    public HikariDataSource dataSource() {
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
