
package org.xmdl.taslak.dao.hibernate;


import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.persister.entity.EntityPersister;

import org.xmdl.ida.lib.test.BaseDaoTestCase; 








/**
 *
 * 'org.xmdl.taslak' package Hibernate Configuration Test Case
 *  
 * $Id$
 *
 * @generated
 */ 
public class HibernateConfigurationTest extends BaseDaoTestCase {

    /**
     * @generated
     */ 
    private SessionFactory sessionFactory;

    /**
     * @generated
     */ 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @generated
     */ 
    public void testColumnMapping() throws Exception {
        Session session = sessionFactory.openSession();
        try {
            Map<?, ?> metadata = sessionFactory.getAllClassMetadata();
            for (Object o : metadata.values()) {
                EntityPersister persister = (EntityPersister) o;
                String className = persister.getEntityName();
                log.debug("Trying select * from: " + className);
                Query q = session.createQuery("from " + className + " c");
                q.iterate();
                log.debug("ok: " + className);
            }
        } finally {
            session.close();
        }
    }
}
