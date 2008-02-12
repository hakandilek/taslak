package org.xmdl.mesken.dao.hibernate;

import java.util.List;

import org.xmdl.ida.lib.dao.hibernate.UniversalDaoHibernate;
import org.xmdl.mesken.dao.LookupDao;
import org.xmdl.mesken.model.Role;

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
