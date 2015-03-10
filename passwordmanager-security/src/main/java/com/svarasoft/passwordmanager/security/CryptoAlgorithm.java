package com.svarasoft.passwordmanager.security;

public enum CryptoAlgorithm {

    MD5("MD5"), SHA1("SHA-1"), SHA256("SHA-256"), SHA384("SHA-384"), SHA512("SHA-512");

    private final String value;

    private CryptoAlgorithm(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Retrieve all available hash algorithms
     *
     * @return a String array containing all available hash algorithms
     */
    public String[] getAvailableAlgorithmValues() {
        //Retrieve all enum values available
        CryptoAlgorithm[] hashes = values();

        //Loop through the available values, and get their string literal
        String[] hashValues = new String[hashes.length];
        for (int i = 0; i < hashes.length; i++) {
            hashValues[i] = hashes[i].toString();
        }
        return hashValues;
    }
}
