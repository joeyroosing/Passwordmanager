package com.svarasoft.passwordmanager.presenters;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import com.svarasoft.passwordmanager.models.Password;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MainPresenter implements Presenter {

    @FXML
    private Parent root;
    @FXML
    private SplitPane contentArea;

    @Inject
    private SafePresenter safePresenter;

    @Inject
    private PasswordDetailPresenter passwordDetailPresenter;

    @Override
    public Parent getView() {
        return root;
    }

    public void showSafeView() {
        safePresenter.loadCategories();
        contentArea.getItems().add(safePresenter.getView());
    }

    public void showPasswordDetailView() {
        contentArea.getItems().add(passwordDetailPresenter.getView());
    }

    public void loadSelectedPassword(Password password) {
        passwordDetailPresenter.setPassword(password);
    }
}