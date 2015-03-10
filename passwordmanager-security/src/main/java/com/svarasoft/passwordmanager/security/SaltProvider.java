package com.svarasoft.passwordmanager.security;

import java.security.SecureRandom;

public class SaltProvider {

    private static final int DEFAULT_SALT_LENGTH = 20;

    public static byte[] generateSalt() {
        return generateSalt(DEFAULT_SALT_LENGTH);
    }

    public static byte[] generateSalt(int saltLen) {
        byte[] salt = new byte[saltLen];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }
}
