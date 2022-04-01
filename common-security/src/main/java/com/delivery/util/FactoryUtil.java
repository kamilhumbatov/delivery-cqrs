package com.delivery.util;

import lombok.SneakyThrows;

import java.security.KeyFactory;

public final class FactoryUtil {
    private static final KeyFactory KEY_FACTORY = createKeyFactory();

    private FactoryUtil() {
        throw new IllegalStateException("no instance!");
    }

    @SneakyThrows
    private static KeyFactory createKeyFactory() {
        return KeyFactory.getInstance("RSA");
    }

    public static KeyFactory getKeyFactory() {
        return KEY_FACTORY;
    }

}
