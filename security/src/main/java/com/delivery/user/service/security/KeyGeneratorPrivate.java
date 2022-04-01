package com.delivery.user.service.security;

import com.delivery.util.FactoryUtil;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

@Component
public class KeyGeneratorPrivate {

    private static final String PRIVATE_KEY = "PRIVATE_KEY";

    @Bean
    public RSAPrivateKey privateKey() throws InvalidKeySpecException, IOException {
        java.security.Security.addProvider(
                new org.bouncycastle.jce.provider.BouncyCastleProvider()
        );

        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(getContent(PRIVATE_KEY));
        return (RSAPrivateKey) FactoryUtil.getKeyFactory().generatePrivate(privKeySpec);
    }


    private byte[] getContent(String fileType) throws IOException {
        Resource resource = new ClassPathResource(fileType);
        String rsaPublicKey = new String(Files.readAllBytes(resource.getFile().toPath()));
        PemReader pemReader = new PemReader(new StringReader(rsaPublicKey));
        return pemReader.readPemObject().getContent();
    }
}
