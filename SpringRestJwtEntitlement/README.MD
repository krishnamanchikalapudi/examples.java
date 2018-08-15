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
