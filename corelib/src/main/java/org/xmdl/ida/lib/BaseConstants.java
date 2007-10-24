package org.xmdl.ida.lib;

public interface BaseConstants {
    /**
     * The name of the ResourceBundle used in this application
     */
    final String BUNDLE_KEY = "ApplicationResources";

    /**
     * File separator from System properties
     */
    final String FILE_SEP = System.getProperty("file.separator");

    /**
     * The name of the configuration hashmap stored in application scope.
     */
    final String CONFIG = "appConfig";

    /**
     * Session scope attribute that holds the locale set by the user. By setting this key
     * to the same one that Struts uses, we get synchronization in Struts w/o having
     * to do extra work or have two session-level variables.
     */
    final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

    /**
     * The name of the CSS Theme setting.
     */
    final String CSS_THEME = "csstheme";
    
    /**
     * User home from System properties
     */
    final String USER_HOME = System.getProperty("user.home") + FILE_SEP;



}
