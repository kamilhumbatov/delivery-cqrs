package com.delivery.util;

import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@Configuration
public class PublicKeyUtility {

    private static final String PUBLIC_KEY = "PUBLIC_KEY";

    @Bean
    public RSAPublicKey publicKey()
            throws InvalidKeySpecException, IOException {

        java.security.Security.addProvider(
                new org.bouncycastle.jce.provider.BouncyCastleProvider());

        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(getContent());

        return (RSAPublicKey) FactoryUtil.getKeyFactory().generatePublic(pubKeySpec);
    }

    private byte[] getContent() throws IOException {
        Resource resource = new ClassPathResource(PUBLIC_KEY);
        String rsaPublicKey = new String(Files.readAllBytes(resource.getFile().toPath()));
        PemReader pemReader = new PemReader(new StringReader(rsaPublicKey));
        return pemReader.readPemObject().getContent();
    }
}

