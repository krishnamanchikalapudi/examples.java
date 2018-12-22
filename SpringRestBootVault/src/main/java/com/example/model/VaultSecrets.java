package com.example.model;

public class VaultSecrets {
    private String username;
    private String password;

    public VaultSecrets() {

    }

    public VaultSecrets(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Credential [username=" + username + ", password=" + password + "]";
    }
}