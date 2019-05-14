package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class K8SecretConfig {
    public K8SecretConfig() {}

    @Value("${db.url}")
    private String dbUrl;
    @Value("${db.username}")
    private String userName;
    @Value("${db.password}")
    private String password;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param dbUserName the dbUserName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the dbPassword
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param dbPassword the dbPassword to set
     */
    public void setPassword(String dbPassword) {
        this.password = dbPassword;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            sb.append("\n----------------------------------------\n");
            sb.append("Configuration properties");
            sb.append("\n \t\t db.url = " + dbUrl);
            sb.append("\n \t\t db.username = "+ userName);
            sb.append("\n \t\t db.password = "+ password);
            sb.append("\n----------------------------------------\n");
        return sb.toString();
    }


}