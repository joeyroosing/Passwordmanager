package com.svarasoft.passwordmanager.init;

import com.google.inject.AbstractModule;
import javafx.fxml.FXMLLoader;
import com.svarasoft.passwordmanager.presenters.MainPresenter;
import com.svarasoft.passwordmanager.presenters.PasswordDetailPresenter;
import com.svarasoft.passwordmanager.presenters.SafePresenter;
import com.svarasoft.passwordmanager.utils.Constants;
import com.svarasoft.passwordmanager.utils.I18n;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Joey on 3/4/2015.
 */
public class GuiceModule extends AbstractModule {
    static final Logger LOG = Logger.getLogger(GuiceModule.class.getName());
    
    @Override
    protected void configure() {
        bind(MainPresenter.class).toInstance(loadPresenter(Constants.MAIN_VIEW_FXML));
        bind(PasswordDetailPresenter.class).toInstance(loadPresenter(Constants.PASSWORD_DETAIL_FXML));
        bind(SafePresenter.class).toInstance(loadPresenter(Constants.SAFE_VIEW_FXML));
    }

    /**
     * Loads an FXML file into memory
     *
     * @param <T> 
     * @param url the path to the fxml file
     * @return the presenter for that fxml file
     */
    protected <T> T loadPresenter(String url) {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();

            // Get the user locale, see if its available, otherwise use default (english)
            I18n.getInstance().setUsedLocale(new Locale("en"));
            ResourceBundle resourceBundle;
            resourceBundle = I18n.getInstance().resolveI18nResources(Constants.BUNDLES_COMMON);

            loader.setResources(resourceBundle);
            loader.load(fxmlStream);
            return loader.getController();
        } catch (IOException ex) {
            Logger.getLogger(GuiceModule.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(String.format(Constants.LOADING_FXML_FAILED, url), ex);
        } finally {
            if (fxmlStream != null)  {
                try  {
                    fxmlStream.close();
                }
                catch (IOException e) {
                    System.out.println("Warning: failed to close FXML file");
                }
            }
        }
    }
}
