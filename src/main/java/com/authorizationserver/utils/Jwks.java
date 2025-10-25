package com.authorizationserver.utils;

import com.nimbusds.jose.jwk.RSAKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

/**
 * @author Joe Grandja
 * @since 0.1.0
 */
public final class Jwks {
    private static final Logger logger = LoggerFactory.getLogger("jwks");

    public static KeyPair generateKeyPair() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
            //storeToFilePem(keyPair,"D:\\");
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    public static RSAKey generateRsa() {
        KeyPair keyPair = generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // @formatter:off
        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        // @formatter:on
    }

    public static RSAKey buildKey() {
        KeyPair keyPair = generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // @formatter:off
        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        // @formatter:on
    }

    public static RSAKey buildKey(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID("1f8896b4-5071-4719-82f8-cab032d8c5e7")
                .build();
    }

    public static void storeToFile(KeyPair keyPair, String path) {
        try (FileOutputStream outPrivate = new FileOutputStream(path + "key.priv"); FileOutputStream outPublic = new FileOutputStream(path + "key.pub")) {
            outPrivate.write(keyPair.getPrivate().getEncoded());
            outPublic.write(keyPair.getPublic().getEncoded());
        } catch (IOException ex) {
            logger.error("store-file", ex);
        }
    }

    public static void storeToFilePem(KeyPair keyPair, String path) {
        try (FileOutputStream outPrivate = new FileOutputStream(path + "key.priv"); FileOutputStream outPublic = new FileOutputStream(path + "key.pub")) {
            outPrivate.write(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()).getBytes());
            outPublic.write(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()).getBytes());
        } catch (IOException ex) {
            logger.error("store-file-pem", ex);
        }
    }

    public static RSAPublicKey loadPublicKey(InputStream inputStream) {
        try {
            KeyFactory publicKeyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(FileCopyUtils.copyToByteArray(inputStream));
            PublicKey publicKey = publicKeyFactory.generatePublic(publicKeySpec);
            return (RSAPublicKey) publicKey;
        } catch (Exception ex) {
            logger.error("load-pubfile", ex);
            return null;
        }
    }

    public static RSAPrivateKey loadPrivateKey(InputStream inputStream) {
        try {
            KeyFactory privateKeyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(FileCopyUtils.copyToByteArray(inputStream));
            PrivateKey privateKey = privateKeyFactory.generatePrivate(privateKeySpec);
            return (RSAPrivateKey) privateKey;
        } catch (Exception ex) {
            logger.error("load-privatefile", ex);
            return null;
        }
    }
}
