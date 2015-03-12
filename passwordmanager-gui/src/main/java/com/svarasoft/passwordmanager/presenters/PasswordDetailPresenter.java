/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svarasoft.passwordmanager.presenters;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import com.svarasoft.passwordmanager.models.Password;

import javax.inject.Singleton;

/**
 * @author Joey
 */
@Singleton
public class PasswordDetailPresenter implements Presenter {

    @FXML
    private Node root;
    @FXML
    private TextField urlTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField categoryTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField descriptionTextArea;

    @Override
    public Node getView() {
        return root;
    }

    public void setPassword(Password password) {
        urlTextField.setText(password.getUrl());
        nameTextField.setText(password.getName());
        usernameTextField.setText(password.getUsername());
        passwordTextField.setText(password.getPassword());
        descriptionTextArea.setText(password.getDescription());
    }
}
