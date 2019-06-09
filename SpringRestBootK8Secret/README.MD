# Pre-requisite
- Java 1.8 or higher
- gradle 4.0 or higher
- [Docker](d)
- [kubectl](https://github.com/kubernetes/kubectl)

# Steps
- [Build jar](./build.sh)
- [Gradle build & unit-test](./README-GRADLE.MD)
- [Build Container image](./build.sh)
- [Container run & unit-test](./README-DOCKER.MD)
- [K8S Deploy & unit-test](./deployment/deploy.sh)
- [K8S UnDeploy script](./deployment/undeploy.sh)
- [API test instructions](./README-REST.MD)

# Dev guidelines
- Execute spring boot service on local machine.
