#!/bin/bash
keytool -keystore test.jks -genkey -keyalg RSA -keysize 4096 -alias test
keytool -keystore test.jks -certreq -alias test -keyalg rsa -file test.csr
openssl x509 -req -CA cacert.pem -CAkey private/cakey.pem -in test.csr -out test.cer -days 365 -CAcreateserial -extfile extfile.conf
keytool -import -keystore test.jks -file cacert.pem -alias ymca
keytool -import -keystore test.jks -file test.cer -alias test

