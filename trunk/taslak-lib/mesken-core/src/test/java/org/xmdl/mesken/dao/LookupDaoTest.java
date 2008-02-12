package org.xmdl.mesken.dao;

import java.util.List;

import org.xmdl.ida.lib.test.BaseDaoTestCase;
import org.xmdl.mesken.dao.LookupDao;
import org.xmdl.mesken.model.Role;

/**
 * This class tests the current LookupDao implementation class
 * @author mraible
 */
public class LookupDaoTest extends BaseDaoTestCase {
    private LookupDao dao;
    
    public void setLookupDao(LookupDao dao) {
        this.dao = dao;
    }

    public void testGetRoles() {
        List<Role> roles = dao.getRoles();
        log.debug(roles);
        assertTrue(roles.size() > 0);
    }
}
