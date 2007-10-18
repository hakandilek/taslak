package org.xmdl.taslak.dao.hibernate;

import java.util.List;

import org.xmdl.ida.lib.dao.hibernate.UniversalDaoHibernate;
import org.xmdl.taslak.dao.LookupDao;
import org.xmdl.taslak.model.Role;

/**
 * Hibernate implementation of LookupDao.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class LookupDaoHibernate extends UniversalDaoHibernate implements LookupDao {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Role> getRoles() {
        log.debug("retrieving all role names...");

        return getHibernateTemplate().find("from Role order by name");
    }
}
