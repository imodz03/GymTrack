package com.elliotb.Helpers;

import com.elliotb.Configuration.ApplicationConfig;

import java.io.FileInputStream;
import java.security.*;
import java.security.cert.Certificate;

public class Keystore {

    public static KeyPair getKeyPairFromKeyStore(ApplicationConfig config) throws Exception {

        FileInputStream is = new FileInputStream("keystore.jks");

        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(is, config.getPassword().toCharArray());

        String alias = config.getKeyalias();

        Key key = keystore.getKey(alias, config.getPassword().toCharArray());
        if (key instanceof PrivateKey) {
            // Get certificate of public key
            Certificate cert = keystore.getCertificate(alias);

            // Get public key
            PublicKey publicKey = cert.getPublicKey();

            // Return a key pair
            return new KeyPair(publicKey, (PrivateKey) key);
        }

        return null;
    }

}
