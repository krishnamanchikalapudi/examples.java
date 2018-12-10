
### Generate jar from WSDL/XSD
``````
mvn clean install
``````

### Cert issue: 'sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target'
- Download/export cert from browser 
- Create keystore
``````
keytool -import -file C:\tools\downloadedCertFromBroser.cer -keystore C:\tools\mavenKeystore
``````
- Generate jar from WSDL/XSD
``````
mvn clean install -X -Djavax.net.ssl.trustStore=C:\tools\mavenKeystore
``````
- Optional: Run as administrator. Import cert to JRE certs
``````
keytool -import -file C:\tools\downloadedCertFromBroser.cer -keystore "C:\Program Files\Java\jdk1.8\jre\lib\security\cacerts"
mvn clean install
``````

