/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.svarasoft.passwordmanager.services;

import com.google.inject.ImplementedBy;
import com.svarasoft.passwordmanager.models.Category;
import com.svarasoft.passwordmanager.services.impl.PasswordManagerServiceImpl;

import java.util.List;


/**
 * @author Joey
 */
@ImplementedBy(PasswordManagerServiceImpl.class)
public interface PasswordManagerService {
    List<Category> getCategories();

    Category getCategory(final String name);
}
