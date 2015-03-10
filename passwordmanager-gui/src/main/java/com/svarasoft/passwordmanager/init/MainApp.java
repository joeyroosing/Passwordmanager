package com.svarasoft.passwordmanager.init;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.svarasoft.passwordmanager.data.DataStorage;
import com.svarasoft.passwordmanager.entities.Category;
import com.svarasoft.passwordmanager.entities.Password;
import com.svarasoft.passwordmanager.presenters.MainPresenter;
import com.svarasoft.passwordmanager.utils.Constants;
import com.svarasoft.passwordmanager.utils.I18n;

import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Injector injector = Guice.createInjector(new GuiceModule());
        MainPresenter mainPresenter = injector.getInstance(MainPresenter.class);
        mainPresenter.showSafeView();
        mainPresenter.showPasswordDetailView();

        Scene scene = new Scene(mainPresenter.getView(), Constants.MAIN_VIEW_WIDTH, Constants.MAIN_VIEW_HEIGHT);
        scene.getStylesheets().add(Constants.MAIN_STYLE);
        stage.setTitle(I18n.getInstance().getI18nString(Constants.I18N_KEY_APP_TITLE));
        stage.setScene(scene);
        stage.setMinWidth(Constants.MAIN_VIEW_WIDTH);
        stage.setMinHeight(Constants.MAIN_VIEW_HEIGHT);
        stage.show();
    }

    /**
     * Functions as a test method to see if writing json works
     */
    @SuppressWarnings("UnusedDeclaration")
    public void writeJson() {
        List<Category> myCats = new ArrayList<>();
        Category cat1 = new Category();
        cat1.setName("cat1");
        cat1.generateId();

        Password pass1 = new Password();
        pass1.setName("pass1");
        pass1.generateId();
        cat1.addPassword(pass1);

        myCats.add(cat1);
        DataStorage.getInstance().saveData(myCats);
    }

    /**
     * Functions as a test method to see if reading json works
     */
    @SuppressWarnings("UnusedDeclaration")
    public void readJson() {
        System.out.println(DataStorage.getInstance().loadData());
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
