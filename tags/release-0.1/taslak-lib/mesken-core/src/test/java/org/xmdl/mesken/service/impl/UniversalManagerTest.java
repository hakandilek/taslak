package org.xmdl.mesken.service.impl;

import org.xmdl.ida.lib.dao.UniversalDao;
import org.xmdl.ida.lib.service.impl.UniversalManagerImpl;
import org.xmdl.ida.lib.test.BaseManagerMockTestCase;
import org.xmdl.mesken.model.User;
import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.test.AssertThrows;

/**
 * This class tests the generic UniversalManager and UniversalManagerImpl implementation.
 */
public class UniversalManagerTest extends BaseManagerMockTestCase {
    protected UniversalManagerImpl manager = new UniversalManagerImpl();
    protected Mock dao;
    
    protected void setUp() throws Exception {
        super.setUp();
        dao = new Mock(UniversalDao.class);
        manager.setDao((UniversalDao) dao.proxy());
    }
    
    protected void tearDown() throws Exception {
        manager = null;
        dao = null;
    }

    /**
     * Simple test to verify BaseDao works.
     */
    public void testCreate() {
        User user = createUser();
        dao.expects(once()).method("save").will(returnValue(user));
        user = (User) manager.save(user);
    }
    
    public void testRetrieve() {
        User user = createUser();
        dao.expects(once()).method("get").will(returnValue(user));
        user = (User) manager.get(User.class, user.getUsername());
    }
    
    public void testUpdate() {
        User user = createUser();
        dao.expects(once()).method("save").isVoid();
        user.getAddress().setCountry("USA");
        user = (User) manager.save(user);
    }
    
    public void testDelete() {
        Exception ex = new ObjectRetrievalFailureException(User.class, "foo");
        dao.expects(once()).method("remove").isVoid();            
        dao.expects(once()).method("get").will(throwException(ex));
        manager.remove(User.class, "foo");
        new AssertThrows(ObjectRetrievalFailureException.class) {
            public void test() {
                manager.get(User.class, "foo");
            }
        }.runTest();
    }
    
    private User createUser() {
        User user = new User();
        // set required fields
        user.setUsername("foo");
        return user;
    }
}
