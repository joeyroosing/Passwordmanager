/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svarasoft.passwordmanager.data;

import com.svarasoft.passwordmanager.security.Crypto;
import org.apache.log4j.Logger;
import com.svarasoft.passwordmanager.models.Category;

import java.util.List;

/**
 * @author Joey
 */
public class DataStorage {

    private static final Logger LOG = Logger.getLogger(DataStorage.class.getName());

    public static final String ENCRYPTION_KEY = "s5v7A1r0a1123497";
    private final Crypto crypto = new Crypto();

    private List<Category> categories;

    private DataStorage() {
        crypto.initEncryptor(ENCRYPTION_KEY);
    }

}
