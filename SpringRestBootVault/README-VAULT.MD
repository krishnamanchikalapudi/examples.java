# Vault setup

1. Download vault software from https://vaultproject.io

2. Starting the Dev Server
`````
vault server -dev -dev-root-token-id="00000000-0000-0000-0000-000000000000" -dev-listen-address="127.0.0.1:8201" & 

`````

3. Set vault address
`````
export VAULT_ADDR=http://127.0.0.1:8200
`````

4. Verify the Server is Running
`````
vault status
`````

5. Writing a Secret
`````
vault kv put secret/springrestbootvault db.url=jdbc:mysql://localhost:3306/springbootdb db.username=HelloName db.password=HelloPassword
`````

6. Getting a Secret
`````
vault kv get secret/springrestbootvault
`````


7. Service PID information
`````
lsof -nP -iTCP:8200
`````





### Reference: https://learn.hashicorp.com/vault/getting-started/first-secret
