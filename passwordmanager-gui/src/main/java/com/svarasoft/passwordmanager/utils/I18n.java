/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.svarasoft.passwordmanager.utils;

import java.util.*;

/**
 * @author Joey
 */
public final class I18n {

    private static I18n instance;
    private Locale usedLocale;

    // Init supported locale
    private static final List<String> supportedLocale = new ArrayList<>();

    static {
        supportedLocale.add(Constants.I18N_DUTCH);
        supportedLocale.add(Constants.I18N_ENGLISH);
        supportedLocale.add(Constants.I18N_CHINESE);
    }

    private I18n() {
    }

    public static I18n getInstance() {
        if (instance == null) {
            instance = new I18n();
        }
        return instance;
    }

    public String getDefaultBundleName() {
        return Constants.BUNDLES_COMMON;
    }

    public String getI18nString(String key) {
        return getI18nString(key, getDefaultBundleName(), usedLocale);
    }

    public String getI18nString(String key, String bundleName) {
        return getI18nString(key, bundleName, usedLocale);
    }

    public String getI18nString(String key, String bundleName, Locale locale) {
        return resolveI18nResources(bundleName, locale).getString(key);
    }

    public void setUsedLocale(Locale locale) {
        this.usedLocale = locale;
    }

    public Locale getUsedLocale() {
        return usedLocale;
    }

    public ResourceBundle resolveI18nResources(String bundleName)
            throws NullPointerException, MissingResourceException {

        return resolveI18nResources(bundleName, usedLocale);
    }

    /**
     * Retrieves and returns the resource bundle based on the language passed in
     * Only ISO 3166 2-letter code, or a UN M.49 3-digit codes are supported
     *
     * @param bundleName the name of the resource bundle
     * @param locale     the 2-letter or 3-digit code to define a language
     * @return a resource bundle based on the passed in language
     */
    public ResourceBundle resolveI18nResources(String bundleName, Locale locale)
            throws NullPointerException, MissingResourceException {

        // if locale is not supported, we default to english
        if (!supportedLocale.contains(locale.getLanguage())) {
            locale = new Locale(Constants.I18N_ENGLISH);
        }

        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle(bundleName, locale);
        } catch (NullPointerException | MissingResourceException ex) {
            throw ex;
        }
        return bundle;
    }
}
