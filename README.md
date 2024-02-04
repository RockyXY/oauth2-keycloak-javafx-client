# oauth2-keycloak-simulator

## SSL

see
also https://unix.stackexchange.com/questions/347116/how-to-create-keystore-and-truststore-using-self-signed-certificate

#### Generate keystore for keycloak

1. Generate a private RSA key  
   ``
   openssl genrsa -out keycloak.key 2048
   ``

2. Create certificate  
   ``
   openssl req -x509 -new -nodes -key keycloak.key -sha256 -days 1024 -out keycloak.pem
   ``  
   Important: Use "localhost" for Common Name for testing locally.

3. Create keystore  
   ``
   openssl pkcs12 -export -name keycloak-cert -in keycloak.pem -inkey keycloak.key -out keycloakkeystore.p12
   ``
4. Convert into JKS keystore  
   ``
   keytool -importkeystore -destkeystore keycloak.keystore -srckeystore serverkeystore.p12 -srcstoretype pkcs12 -alias keycloak-cert
   ``

#### Generate truststore for client

1. Create truststore for client  
   ``
   keytool -import -alias server-cert -file keycloak.pem -keystore client.truststore
   ``

#### Start keycloak with keystore

``
bin/kc.bat start --https-key-store-file=keycloak.keystore --https-key-store-password=******
``