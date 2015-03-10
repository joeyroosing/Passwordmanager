/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svarasoft.passwordmanager.security;

import org.jasypt.digest.PooledStringDigester;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.salt.ByteArrayFixedSaltGenerator;

/**
 * @author Joey
 */
public class PooledCrypto {

    PooledPBEStringEncryptor cryptor;
    PooledStringDigester digester;

    /**
     * Initializes an encryption object
     *
     * @param key the password to use for encryption/decryption
     * @return an initialized encryptor object
     */
    public void initEncryptor(final String key) {
        initEncryptor(null, key);
    }

    /**
     * Initializes an encryption object
     *
     * @param salt the salt to add to the encryption
     * @param key  the password to use for encryption/decryption
     * @return an initialized encryptor object
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
     * @return an initialized encryptor object
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
     * @return an initialized encryptor object
     */
    public void initEncryptor(final byte[] salt, final String key,
                                                         final CryptoAlgorithm algorithm, int keyObtentionIterations) {
        initEncryptor(salt, key, algorithm, keyObtentionIterations,
                Runtime.getRuntime().availableProcessors());
    }

    /**
     * Initializes an encryption object
     *
     * @param salt                   the salt to add to the encryption
     * @param key                    the password to use for encryption/decryption
     * @param algorithm              the algorithm to encrypt with
     * @param keyObtentionIterations the number of hashing iterations applied to
     *                               obtain the encryption key.
     * @param cores                  the number of cores to use
     * @return an initialized encryptor object
     */
    public void initEncryptor(final byte[] salt, final String key,
                                                         final CryptoAlgorithm algorithm, int keyObtentionIterations, int cores) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPassword(key);
        if (salt != null && salt.length > 0) {
            encryptor.setSaltGenerator(new ByteArrayFixedSaltGenerator(salt));
        }
        if (algorithm != null) {
            encryptor.setAlgorithm(algorithm.toString());
        }
        encryptor.setKeyObtentionIterations(keyObtentionIterations);
        encryptor.setPoolSize(cores);
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

    public void initDigester() {
        initDigester(null);
    }

    public void initDigester(byte[] salt) {
        initDigester(salt, CryptoAlgorithm.SHA256);
    }

    public void initDigester(byte[] salt, CryptoAlgorithm algorithm) {
        initDigester(salt, algorithm, 1);
    }

    public void initDigester(byte[] salt, CryptoAlgorithm algorithm, int iterations) {
        initDigester(salt, algorithm, 1,
                Runtime.getRuntime().availableProcessors());
    }

    public void initDigester(byte[] salt, CryptoAlgorithm algorithm, int iterations, int cores) {
        PooledStringDigester digester = new PooledStringDigester();
        if (salt != null) {
            digester.setSaltGenerator(new ByteArrayFixedSaltGenerator(salt));
            digester.setSaltSizeBytes(salt.length);
        }
        digester.setPoolSize(cores);
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
