package org.xmdl.ida.lib.dao;

import java.util.Date;

import org.xmdl.ida.lib.dao.UniversalDao;
import org.xmdl.ida.lib.model.Dummy;
import org.xmdl.ida.lib.test.BaseDaoTestCase;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * This class tests the generic GenericDao and BaseDao implementation.
 */
public class UniversalDaoTest extends BaseDaoTestCase {
    protected UniversalDao universalDao;

    /**
     * This method is used instead of setUniversalDao b/c setUniversalDao uses
     * autowire byType <code>setPopulateProtectedVariables(true)</code> can also
     * be used, but it's a little bit slower.
     */
    public void onSetUpBeforeTransaction() throws Exception {
        universalDao = (UniversalDao) applicationContext.getBean("universalDao");
    }

    public void onTearDownAfterTransaction() throws Exception {
        universalDao = null;
    }

    /**
     * Simple test to verify CRUD works.
     */
    public void testCRUD() {
        Dummy bean = new Dummy();
        // set required fields
        bean.setName("foo");
        bean.setValue(42.0);
        Date now = new Date();
		bean.setDate(now );

        // create
        bean = (Dummy)universalDao.save(bean);
        flush();
        assertNotNull(bean.getId());

        // retrieve
        bean = (Dummy) universalDao.get(Dummy.class, bean.getId());
        assertNotNull(bean);
        assertEquals("foo", bean.getName());
        assertEquals(42.0, bean.getValue());
        assertEquals(now, bean.getDate());

        // update
        bean.setValue(42.42);
        universalDao.save(bean);
        flush();

        bean = (Dummy) universalDao.get(Dummy.class, bean.getId());
        assertEquals(42.42, bean.getValue());

        // delete
        universalDao.remove(Dummy.class, bean.getId());
        flush();
        try {
            universalDao.get(Dummy.class, bean.getId());
            fail("Dummy 'foo' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        } catch (InvalidDataAccessApiUsageException e) { // Spring 2.0 throws this one
            assertNotNull(e.getMessage());
        }
    }
}
