# Pre-requisite
- Java 1.8 or higher
- gradle 4.0 or higher

# Steps
- [Gradle build instructions](./README-GRADLE.MD)
- [API test instructions](./README-REST.MD)

# Dev guidelines
- Execute spring boot service on local machine.
- To start securing, I want to avoid exposing http://localhost:8080/v1/${id} to everyone, so I will create a configuration that forbids this.
- Everyone can access /v1/, and the /v1/live route using POST/GET.
- Secure /v1/${id} '/v1/123' to users whom can provide a valid JWT token.


# JWT Token
``````````
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJwZXJzb24iXSwiY2xpZW50X2lkIjoiMTIzNDU2Nzg5MCIsIm5hbWUiOiJLcmlzaG5hIE1hbmNoaWthbGFwdWRpIiwibGlua2VkaW4iOiJodHRwczovL3d3dy5saW5rZWRpbi5jb20vaW4va3Jpc2huYW1hbmNoaWthbGFwdWRpLyJ9.zh9txRmkLZj6B6e0LsOIu0N2wkpgMXuOPdo0Lj2XjV4
``````````
# JWT Decoded
- Header:ALGORITHM & TOKEN TYPE
``````````
{
  "alg": "HS256",
  "typ": "JWT"
}
``````````

- Payload:DATA
``````````
{
  "scope": [
    "person"
  ],
  "client_id": "1234567890",
  "name": "Krishna Manchikalapudi",
  "linkedin": "https://www.linkedin.com/in/krishnamanchikalapudi/"
}
``````````

- Signature
``````````
HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload), KrishnaManchikalapudi
)
``````````
