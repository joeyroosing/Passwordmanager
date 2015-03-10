/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svarasoft.passwordmanager.entities;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Joey
 */
public class Category {

    private String id;
    private String name;
    private List<Password> passwords;

    public Category() {
    }

    public String getId() {
        return id;
    }

    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Password> getPasswords() {
        return passwords;
    }

    public void setPasswords(ObservableList<Password> passwords) {
        this.passwords = passwords;
    }

    public void addPassword(Password password) {
        if (passwords == null) {
            passwords = new ArrayList<>();
        }
        passwords.add(password);
    }

    @Override
    public String toString() {
        return name;
    }
}
