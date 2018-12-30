package com.example.config;

import com.example.constant.Constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.vault.annotation.VaultPropertySource;
import org.springframework.vault.annotation.VaultPropertySource.Renewal;

@ConfigurationProperties("db")

public class VaultConfig {
    public VaultConfig() {}

    //@Value("${db.username}")
    private String userName;

    //@Value("${db.password}")
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
            sb.append(" db.username = "+ userName);
            sb.append("\n db.password = "+ password);
        return sb.toString();
    }


}