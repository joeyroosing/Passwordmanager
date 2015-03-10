/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svarasoft.passwordmanager.daos.impl;


import com.svarasoft.passwordmanager.daos.CategoryDao;
import com.svarasoft.passwordmanager.data.DataStorage;
import com.svarasoft.passwordmanager.entities.Category;

import java.util.List;

/**
 * @author Joey
 */
public class CategoryDaoImpl implements CategoryDao {

    @Override
    public List<Category> allCategories() {
        return DataStorage.getInstance().loadData();
    }
}
