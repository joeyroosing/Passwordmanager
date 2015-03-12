/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svarasoft.passwordmanager.dao;

import com.google.inject.ImplementedBy;
import com.svarasoft.passwordmanager.dao.impl.CategoryDaoImpl;
import com.svarasoft.passwordmanager.models.Category;

import java.util.List;


/**
 * @author Joey
 */
@ImplementedBy(CategoryDaoImpl.class)
public interface CategoryDao {

    List<Category> allCategories();
}
