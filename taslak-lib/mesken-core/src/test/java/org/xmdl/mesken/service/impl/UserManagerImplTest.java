package org.xmdl.mesken.service.impl;

import org.xmdl.ida.lib.test.BaseManagerMockTestCase;
import org.xmdl.mesken.MeskenConstants;
import org.xmdl.mesken.dao.RoleDao;
import org.xmdl.mesken.dao.UserDao;
import org.xmdl.mesken.model.Role;
import org.xmdl.mesken.model.User;
import org.xmdl.mesken.service.UserExistsException;
import org.xmdl.mesken.service.impl.RoleManagerImpl;
import org.xmdl.mesken.service.impl.UserManagerImpl;
import org.jmock.Mock;
import org.springframework.dao.DataIntegrityViolationException;

public class UserManagerImplTest extends BaseManagerMockTestCase {
    //~ Instance fields ========================================================
    private UserManagerImpl userManager = new UserManagerImpl();
    private RoleManagerImpl roleManager = new RoleManagerImpl();
    private Mock userDao = null;
    private Mock roleDao = null;

    //~ Methods ================================================================

    protected void setUp() throws Exception {
        super.setUp();
        userDao = new Mock(UserDao.class);
        userManager.setUserDao((UserDao) userDao.proxy());
        roleDao = new Mock(RoleDao.class);
        roleManager.setRoleDao((RoleDao) roleDao.proxy());
    }
    
    public void testGetUser() throws Exception {
        User testData = new User("1");
        testData.getRoles().add(new Role("user"));
        // set expected behavior on dao
        userDao.expects(once()).method("get")
               .with(eq(1L)).will(returnValue(testData));
        
        User user = userManager.getUser("1");
        assertTrue(user != null);
        assertTrue(user.getRoles().size() == 1);
    }

    public void testSaveUser() throws Exception {
        User testData = new User("1");
        testData.getRoles().add(new Role("user"));
        // set expected behavior on dao
        userDao.expects(once()).method("get")
               .with(eq(1L)).will(returnValue(testData));
        
        User user = userManager.getUser("1");
        user.setPhoneNumber("303-555-1212");

        userDao.expects(once()).method("saveUser").with(same(user)).will(returnValue(user));
        
        user = userManager.saveUser(user);
        assertTrue(user.getPhoneNumber().equals("303-555-1212"));
        assertTrue(user.getRoles().size() == 1);
    }

    public void testAddAndRemoveUser() throws Exception {
        User user = new User();

        // call populate method in super class to populate test data
        // from a properties file matching this class name
        user = (User) populate(user);
        
        // set expected behavior on role dao
        roleDao.expects(once()).method("getRoleByName")
               .with(eq("ROLE_USER")).will(returnValue(new Role("ROLE_USER")));
        
        Role role = roleManager.getRole(MeskenConstants.USER_ROLE);
        user.addRole(role);

        // set expected behavior on user dao
        userDao.expects(once()).method("saveUser").with(same(user)).will(returnValue(user));
        
        user = userManager.saveUser(user);
        assertTrue(user.getUsername().equals("john"));
        assertTrue(user.getRoles().size() == 1);
        
        userDao.expects(once()).method("remove").with(eq(5L));
        userManager.removeUser("5");

        userDao.expects(once()).method("get").will(returnValue(null));
        user = userManager.getUser("5");
        assertNull(user);
    }
    
    public void testUserExistsException() {
        // set expectations
        User user = new User("admin");
        user.setEmail("matt@raibledesigns.com");

        Exception ex = new DataIntegrityViolationException("");
        userDao.expects(once()).method("saveUser").with(same(user))
               .will(throwException(ex));
        
        // run test
        try {
            userManager.saveUser(user);
            fail("Expected UserExistsException not thrown");
        } catch (UserExistsException e) {
            log.debug("expected exception: " + e.getMessage());
            assertNotNull(e);
        }
    }
}
