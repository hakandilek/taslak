package org.xmdl.taslak.service.impl;

import org.xmdl.ida.lib.model.LabelValue;
import org.xmdl.ida.lib.test.BaseManagerMockTestCase;
import org.xmdl.taslak.dao.LookupDao;
import org.xmdl.taslak.model.Role;
import org.xmdl.taslak.TaslakConstants;
import org.jmock.Mock;

import java.util.ArrayList;
import java.util.List;


public class LookupManagerImplTest extends BaseManagerMockTestCase {
    private LookupManagerImpl mgr = new LookupManagerImpl();
    private Mock lookupDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        lookupDao = new Mock(LookupDao.class);
        mgr.setLookupDao((LookupDao) lookupDao.proxy());
    }

    public void testGetAllRoles() {
        log.debug("entered 'testGetAllRoles' method");

        // set expected behavior on dao
        Role role = new Role(TaslakConstants.ADMIN_ROLE);
        List<Role> testData = new ArrayList<Role>();
        testData.add(role);
        lookupDao.expects(once()).method("getRoles").withNoArguments().will(returnValue(testData));

        List<LabelValue> roles = mgr.getAllRoles();
        assertTrue(roles.size() > 0);
    }
}
