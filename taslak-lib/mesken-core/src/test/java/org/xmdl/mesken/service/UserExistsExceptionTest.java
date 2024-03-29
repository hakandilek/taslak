package org.xmdl.mesken.service;

import org.xmdl.ida.lib.test.BaseManagerTestCase;
import org.xmdl.mesken.model.User;
import org.xmdl.mesken.service.UserExistsException;
import org.xmdl.mesken.service.UserManager;
import org.springframework.beans.BeanUtils;

public class UserExistsExceptionTest extends BaseManagerTestCase {
    private UserManager manager = null;

    public void setUserManager(UserManager userManager) {
        this.manager = userManager;
    }
    
    protected String[] getConfigLocations() {
        setAutowireMode(AUTOWIRE_BY_NAME);
        return new String[] {
        		"classpath*:**/applicationContext*.xml",
        };
    }

    public void testAddExistingUser() throws Exception {
        logger.debug("entered 'testAddExistingUser' method");
        assertNotNull(manager);

        User user = manager.getUser("1");
        
        // create new object with null id - Hibernate doesn't like setId(null)
        User user2 = new User();
        BeanUtils.copyProperties(user, user2);
        user2.setId(null);
        user2.setVersion(null);
        user2.setRoles(null);
        
        // try saving as new user, this should fail b/c of unique keys
        try {
            manager.saveUser(user2);
            fail("Duplicate user didn't throw UserExistsException");
        } catch (UserExistsException uee) {
            assertNotNull(uee);
        }
    }    
}
