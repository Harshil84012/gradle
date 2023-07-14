package com.sungjun.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class User {

    @Autowired
    private Environment env;

    @GetMapping("/message")
    @PreAuthorize("hasRole('ROLE_DOCTOR') and hasPermission('forDoctor','write') || hasPermission('forDoctor','read') || hasPermission('forDoctor','update')")
    public String getOwnerName() {
        System.out.println("env.getProperty(\"owner.name\") ::: " + env.getProperty("owner.name"));
        System.out.println("env.getProperty(\"spring.datasource.username\") = " + env.getProperty("spring.datasource.username"));
        System.out.println("env.getProperty(\"spring.datasource.password\") = " + env.getProperty("spring.datasource.password"));
        return env.getProperty("owner.name");
    }
}
