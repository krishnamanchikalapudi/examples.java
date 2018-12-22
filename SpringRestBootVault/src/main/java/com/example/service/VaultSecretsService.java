package com.example.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.core.env.VaultPropertySource;
import org.springframework.vault.support.VaultResponse;
import org.springframework.vault.support.VaultResponseSupport;

@Service
public class VaultSecretsService {

    @Autowired
    private VaultTemplate vaultTemplate;

    /**
     * To Secure Credentials
     * 
     * @param credentials
     * @return VaultResponse
     * @throws URISyntaxException
     */
    public void secureCredentials(Credentials  credentials) throws URISyntaxException {

        vaultTemplate.write(Constants.VAULT_SECRET, credentials);
    }

    /**
     * To Retrieve Credentials
     * 
     * @return Credentials
     * @throws URISyntaxException
     */
    public Credentials accessCredentials() throws URISyntaxException {

        VaultResponseSupport<Credentials> response = vaultTemplate.read(Constants.VAULT_SECRET, Credentials.class);
        return response.getData();
    }
}