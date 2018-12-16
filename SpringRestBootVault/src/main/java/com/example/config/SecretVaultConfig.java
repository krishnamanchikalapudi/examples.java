package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("springrestbootvault")
public class SecretVaultConfig {
    private String username;
    private String password;

    public String getUsername() {
        System.out.println("username: " + username);
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        System.out.println("password: " + password);
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}