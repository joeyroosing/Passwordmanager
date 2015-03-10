/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svarasoft.passwordmanager.presenters;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.svarasoft.passwordmanager.entities.Category;
import com.svarasoft.passwordmanager.entities.Password;
import com.svarasoft.passwordmanager.services.PasswordManagerService;
import com.svarasoft.passwordmanager.utils.Constants;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Joey
 */
@Singleton
public class SafePresenter implements Presenter, Initializable {

    private final Image categoryIcon = new Image(
            getClass().getResourceAsStream(Constants.CATEGORY_ICON));
    private final Image passwordIcon = new Image(
            getClass().getResourceAsStream(Constants.PASSWORD_ICON));
    @FXML
    private Node root;
    @FXML
    private TreeView<Object> categoryTree;
    @Inject
    private MainPresenter mainPresenter;
    @Inject
    private PasswordManagerService passwordManagerService;


    private List<Category> categories;

    @Override
    public Node getView() {
        return root;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoryTree.showRootProperty().set(false);

        categoryTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            categoryTreeSelectionChanged(newValue);
        });
    }

    private void categoryTreeSelectionChanged(TreeItem<Object> newValue) {
        Object value = newValue.getValue();
        if (value instanceof Password) {
            Password password = ((Password) value);
            mainPresenter.loadSelectedPassword(password);
        }
    }


    void loadCategories() {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        categories.addAll(passwordManagerService.getCategories());

        TreeItem<Object> rootItem = new TreeItem<>(new Category()); //root item is just a dummy category

        categories.stream().forEach((category) -> {
            TreeItem<Object> categoryItem = createTreeItem(rootItem, category, categoryIcon);
            category.getPasswords().stream().forEach((password) -> createTreeItem(categoryItem, password, passwordIcon));
        });
        categoryTree.setRoot(rootItem);
        categoryTree.setShowRoot(false);
    }

    private TreeItem<Object> createTreeItem(TreeItem<Object> parent, Object item, Image icon) {
        TreeItem<Object> treeItem = new TreeItem<>(item, new ImageView(icon));
        treeItem.setExpanded(true);
        if (parent != null) {
            parent.getChildren().add(treeItem);
        }

        return treeItem;
    }
}
