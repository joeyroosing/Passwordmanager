/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.svarasoft.passwordmanager.services.impl;


import com.svarasoft.passwordmanager.daos.CategoryDao;
import com.svarasoft.passwordmanager.entities.Category;
import com.svarasoft.passwordmanager.services.PasswordManagerService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * @author Joey
 */
@Singleton
public final class PasswordManagerServiceImpl implements PasswordManagerService {

    @Inject
    CategoryDao categoryDao;

    @Override
    public List<Category> getCategories() {
        return categoryDao.allCategories();
    }

    @Override
    public Category getCategory(final String name) {

        List<Category> categories = getCategories();
        for (Category category : categories) {
            if (category.getName().equals(name)) {
                return category;
            }
            break;
        }
        return null;
    }

}
