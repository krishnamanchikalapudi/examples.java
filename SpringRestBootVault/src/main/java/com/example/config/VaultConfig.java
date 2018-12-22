package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;

@Configuration
public class VaultConfig extends AbstractVaultConfiguration {

    @Override
    public ClientAuthentication clientAuthentication() {
        return new TokenAuthentication("s.4JjDMusvhOWjuE3jtTn90oAG");
    }

    @Override
    public VaultEndpoint vaultEndpoint() {
        return VaultEndpoint.create("host", 8200);
    }
    
}