package com.cricketbetting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() 
    {
    	// https://freedb.tech/dashboard/index.php
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://sql.freedb.tech:3306/freedb_cbet_db");
        dataSource.setUsername("freedb_lokeshDB");
        dataSource.setPassword("?8wKy$2pnC*AavD");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) 
    {
        return new JdbcTemplate(dataSource);
    }
}