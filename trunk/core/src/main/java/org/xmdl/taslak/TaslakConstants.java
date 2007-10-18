package org.xmdl.taslak;

import org.xmdl.ida.lib.BaseConstants;


/**
 * Constant values used throughout the application.
 * 
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface TaslakConstants extends BaseConstants {

    /**
     * The encryption algorithm key to be used for passwords
     */
    final String ENC_ALGORITHM = "algorithm";

    /**
     * A flag to indicate if passwords should be encrypted
     */
    final String ENCRYPT_PASSWORD = "encryptPassword";

    /**
     * User home from System properties
     */
    final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

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
