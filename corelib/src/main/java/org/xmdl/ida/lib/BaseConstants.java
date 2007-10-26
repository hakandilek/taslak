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


    //TODO: Separate the following, those are specific to mesken application
    
	/**
     * The encryption algorithm key to be used for passwords
     */
    final String ENC_ALGORITHM = "algorithm";

    /**
     * A flag to indicate if passwords should be encrypted
     */
    final String ENCRYPT_PASSWORD = "encryptPassword";

    /**
     * The request scope attribute under which an editable user form is stored
     */
    final String USER_KEY = "userForm";

    /**
     * The request scope attribute that holds the user list
     */
    final String USER_LIST = "userList";

    /**
     * The request scope attribute for indicating a newly-registered user
     */
    final String REGISTERED = "registered";

    /**
     * The name of the Administrator role, as specified in web.xml
     */
    final String ADMIN_ROLE = "ROLE_ADMIN";

    /**
     * The name of the User role, as specified in web.xml
     */
    final String USER_ROLE = "ROLE_USER";

    /**
     * The name of the user's role list, a request-scoped attribute
     * when adding/editing a user.
     */
    final String USER_ROLES = "userRoles";

    /**
     * The name of the available roles list, a request-scoped attribute
     * when adding/editing a user.
     */
    final String AVAILABLE_ROLES = "availableRoles";



}
