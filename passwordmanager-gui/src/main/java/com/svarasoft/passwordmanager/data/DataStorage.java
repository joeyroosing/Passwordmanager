/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svarasoft.passwordmanager.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.svarasoft.passwordmanager.security.Crypto;
import org.apache.log4j.Logger;
import com.svarasoft.passwordmanager.entities.Category;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import com.svarasoft.passwordmanager.utils.EncodingUtils;

/**
 * @author Joey
 */
public class DataStorage {

    private static final Logger LOG = Logger.getLogger(DataStorage.class.getName());

    private static final Type TOKEN_TYPE = new TypeToken<ArrayList<Category>>() {
    }.getType();
    private static final String JSON_FILE = "./svarasoft.json";
    public static final String ENCRYPTION_KEY = "s5v7A1r0a1123497";
    private final Crypto crypto = new Crypto();

    private List<Category> categories;

    private DataStorage() {
        crypto.initEncryptor(ENCRYPTION_KEY);
    }

    public static DataStorage getInstance() {
        return DataStorageHolder.INSTANCE;
    }

    private static class DataStorageHolder {

        private static final DataStorage INSTANCE = new DataStorage();
    }

    public List<Category> loadData() {
        if (categories == null || categories.isEmpty()) {
            categories = loadDataFromJson();
        }
        return categories;
    }

    public List<Category> refreshData() {

        return categories;
    }

    public void saveData(List<Category> newCategories) {
        saveDataToJson(newCategories);
    }

    /**
     * Deserialize json into objects read from a file where its content is
     * encrypted
     *
     * @return a list of categories
     */
    private List<Category> loadDataFromJson() {
        String json = readJsonFromFile();
        Gson gson = new Gson();
        categories = gson.fromJson(json, TOKEN_TYPE);
        return categories;
    }

    /**
     * Serializes the input to json and writes it to disc
     *
     * @param categories the object to serialize
     */
    private void saveDataToJson(List<Category> categories) {
        Gson gson = new Gson();
        String json = gson.toJson(categories);
        String encryptedJson = crypto.encrypt(json);
        writeJsonToFile(EncodingUtils.encodeUTF8(encryptedJson));
    }

    /**
     * Read a file containing <b>encrypted</b> json
     *
     * @return the decrypted json or null
     */
    public String readJsonFromFile() {
        try {
            byte[] content = Files.readAllBytes(Paths.get(JSON_FILE));
            //return EncodingUtils.decodeUTF8(content);
            return crypto.decrypt(EncodingUtils.decodeUTF8(content));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * Writes the <b>encrypted</b> json to a file
     *
     * @param json the encrypted json to be written to file
     * @return true if write is is success, otherwise false
     */
    public boolean writeJsonToFile(final byte[] json) {
        try {
            Files.write(Paths.get(JSON_FILE), json, StandardOpenOption.CREATE);
            return true;
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return false;
    }
}
