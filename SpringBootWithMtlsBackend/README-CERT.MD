# Client Cert
- Cert ceration password: ChangeSecr3t

## Create A Self Signed Client Cert
````
keytool -genkeypair -alias client-private-cert -keyalg RSA -keysize 2048 -storetype JKS -keystore client-private-cert.jks -validity 3650 -ext SAN=dns:localhost,ip:127.0.0.1 
````
### output
````
Enter keystore password:  
Re-enter new password: 
What is your first and last name?
  [Unknown]:  Client cert
What is the name of your organizational unit?
  [Unknown]:  Client cert
What is the name of your organization?
  [Unknown]:  Client cert
What is the name of your City or Locality?
  [Unknown]:  Client
What is the name of your State or Province?
  [Unknown]:  Client
What is the two-letter country code for this unit?
  [Unknown]:  US
Is CN=Client cert, OU=Client cert, O=Client cert, L=Client, ST=Client, C=US correct?
  [no]:  yes

Enter key password for <client-private-cert>
	(RETURN if same as keystore password):  
Re-enter new password: 

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore client-private-cert.jks -destkeystore client-private-cert.jks -deststoretype pkcs12".
````


## Convert client-private-cert.jks to PKCS12
````
keytool -importkeystore -srckeystore client-private-cert.jks -destkeystore client-private-cert.jks -deststoretype pkcs12
````
### output
````
Enter source keystore password:  
Entry for alias client-private-cert successfully imported.
Import command completed:  1 entries successfully imported, 0 entries failed or cancelled

Warning:
Migrated "client-private-cert.jks" to Non JKS/JCEKS. The JKS keystore is backed up as "client-private-cert.jks.old".
````


## Verify the certificate is in the TrustStore using the keystore -list command:
````
keytool -list -keystore client-private-cert.jks -storepass ClientSecr3t
````
### output
````
Enter keystore password:  
Keystore type: PKCS12
Keystore provider: SUN

Your keystore contains 1 entry

client-private-cert, Sep 21, 2021, PrivateKeyEntry, 
Certificate fingerprint (SHA1): 20:0F:9A:F7:9A:A2:77:3B:65:77:5C:18:DD:D7:55:8C:32:6C:F4:D7
````


## Create public certificate file from client cert:
````
keytool -export -alias client-private-cert -file client-public-cert.crt -keystore client-private-cert.jks
````
### output
````
Enter keystore password:  
Certificate stored in file <client-public-cert.crt>
````


# Java cacerts
- java default password: changeit
## List java cacert
````
keytool -list -keystore $JAVA_HOME/jre/lib/security/cacerts
````
### output
````
Enter keystore password:  
Keystore type: jks
Keystore provider: SUN

Your keystore contains 155 entries

hellenicacademicandresearchinstitutionsrootca2011, Aug 27, 2018, trustedCertEntry, 
Certificate fingerprint (SHA1): FE:45:65:9B:79:03:5B:98:A1:61:B5:51:2E:AC:DA:58:09:48:22:4D
taiwangrca, Aug 27, 2018, trustedCertEntry, 
````

## Add cert to java cacert
Note: execute below command in admin mode
````
keytool -import -alias client-public-cert -keystore $JAVA_HOME/jre/lib/security/cacerts -file client-public-cert.crt
````
### output
````
[Enter keystore password:  
Owner: CN=Client cert, OU=client cert, O=client cert, L=San Francisco, ST=CA, C=US
Issuer: CN=Client cert, OU=client cert, O=client cert, L=San Francisco, ST=CA, C=US
Serial number: 21799a2b
Valid from: Tue Sep 21 13:28:20 PDT 2021 until: Fri Sep 19 13:28:20 PDT 2031
Certificate fingerprints:
	 MD5:  7F:AE:30:3C:16:6F:F8:AD:F9:D0:3C:F7:65:BB:7C:19
	 SHA1: 20:0F:9A:F7:9A:A2:77:3B:65:77:5C:18:DD:D7:55:8C:32:6C:F4:D7
	 SHA256: 1B:ED:C9:CD:01:5D:7B:A7:7D:17:C3:DB:72:BA:56:51:A5:9D:32:6D:10:9D:D7:E1:52:93:F2:F2:6A:E9:38:86
Signature algorithm name: SHA256withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 3

Extensions: 

#1: ObjectId: 2.5.29.17 Criticality=false
SubjectAlternativeName [
  DNSName: localhost
  IPAddress: 127.0.0.1
]

#2: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: 2F B1 BF 5C A8 32 ED D9   39 5D 7B 16 C4 65 09 74  /..\.2..9]...e.t
0010: F1 B6 BD 3C                                        ...<
]
]

Trust this certificate? [no]:  yes
Certificate was added to keystore
````

