//package com.sungjun.api.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class JpaConfig {
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public DataSource dataSource()
//    {
//
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
//        dataSourceBuilder.url("jdbc:mysql://localhost:3307/hospital_management");
//        dataSourceBuilder.username(env.getProperty("spring.datasource.username"));
//        dataSourceBuilder.password(env.getProperty("spring.datasource.password"));
//        return dataSourceBuilder.build();
//    }
//}