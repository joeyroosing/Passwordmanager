/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svarasoft.passwordmanager.security;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ByteArrayFixedSaltGenerator;

/**
 * @author Joey
 */
public final class Crypto {

    private StandardPBEStringEncryptor cryptor;
    private StandardStringDigester digester;

    /**
     * Initializes an encryption object
     *
     * @param key the password to use for encryption/decryption
     */
    public void initEncryptor(final String key) {
        initEncryptor(null, key);
    }

    /**
     * Initializes an encryption object
     *
     * @param salt the salt to add to the encryption
     * @param key  the password to use for encryption/decryption
     */
    public void initEncryptor(final byte[] salt, final String key) {
        initEncryptor(null, key, null);
    }

    /**
     * Initializes an encryption object
     *
     * @param salt      the salt to add to the encryption
     * @param key       the password to use for encryption/decryption
     * @param algorithm the algorithm to encrypt with
     */
    public void initEncryptor(final byte[] salt, final String key,
                              final CryptoAlgorithm algorithm) {
        initEncryptor(null, key, algorithm, 1);
    }

    /**
     * Initializes an encryption object
     *
     * @param salt                   the salt to add to the encryption
     * @param key                    the password to use for encryption/decryption
     * @param algorithm              the algorithm to encrypt with
     * @param keyObtentionIterations the number of hashing iterations applied to
     *                               obtain the encryption key.
     */
    public void initEncryptor(final byte[] salt, final String key,
                              final CryptoAlgorithm algorithm, int keyObtentionIterations) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(key);
        if (salt != null && salt.length > 0) {
            encryptor.setSaltGenerator(new ByteArrayFixedSaltGenerator(salt));
        }
        if (algorithm != null) {
            encryptor.setAlgorithm(algorithm.toString());
        }
        encryptor.setKeyObtentionIterations(keyObtentionIterations);
        this.cryptor = encryptor;
    }

    /**
     * Encrypts a message
     *
     * @param message the message to encrypt
     * @return the encrypted message, or null if the cryptor is not initialized with
     * initEncryptor
     */
    public String encrypt(String message) {
        if (cryptor != null) {
            return null;
        }
        return cryptor.encrypt(message);
    }

    /**
     * Decrypts a message
     *
     * @param message the message to decrypt
     * @return the decrypted message, or null if the cryptor is not initialized with initEncryptor
     */
    public String decrypt(String message) {
        if (cryptor == null) {
            return null;
        }
        return cryptor.decrypt(message);
    }

    /**
     * Initializes an digester object
     *
     * @return an initialized digester object
     */
    public void initDigester() {
        initDigester(null);
    }

    /**
     * Initializes an digester object
     *
     * @param salt the salt to add tot he to be hashed value
     * @return an initialized digester object
     */
    public void initDigester(byte[] salt) {
        initDigester(salt, CryptoAlgorithm.SHA256);
    }

    /**
     * Initializes an digester object
     *
     * @param salt      the salt to add tot he to be hashed value
     * @param algorithm the hashing algorithm to use
     * @return an initialized digester object
     */
    public void initDigester(byte[] salt, CryptoAlgorithm algorithm) {
        initDigester(salt, algorithm, 500);
    }

    /**
     * Initializes an digester object
     *
     * @param salt       the salt to add tot he to be hashed value
     * @param algorithm  the hashing algorithm to use
     * @param iterations the amount of iterations to hash the value
     * @return an initialized digester object
     */
    public void initDigester(byte[] salt, CryptoAlgorithm algorithm, int iterations) {
        StandardStringDigester digester = new StandardStringDigester();
        if (salt != null && salt.length > 0) {
            digester.setSaltGenerator(new ByteArrayFixedSaltGenerator(salt));
            digester.setSaltSizeBytes(salt.length);
        }
        digester.setAlgorithm(algorithm.toString());
        digester.setIterations(iterations);
        this.digester = digester;
    }

    public String digest(String message) {
        if (digester == null) {
            return null;
        }
        return digester.digest(message);
    }
}
