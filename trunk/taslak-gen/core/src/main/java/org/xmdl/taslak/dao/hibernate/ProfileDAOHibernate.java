
package org.xmdl.taslak.dao.hibernate;


import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.Session;

import org.hibernate.criterion.Restrictions;

import org.xmdl.ida.lib.dao.hibernate.GenericDaoHibernate;

import org.xmdl.mesken.model.User;

import org.xmdl.taslak.dao.*;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*; 












/**
 *
 * Profile DAO Hibernate implementation
 *  
 * $Id$
 *
 * @generated
 */ 
public class ProfileDAOHibernate extends GenericDaoHibernate<Profile, Long> implements ProfileDAO {

    /**
     * Public default constructor
     * @generated
     */ 
    public ProfileDAOHibernate() {
        super(Profile.class);
    }

    /**
     * @generated
     */ 
    public Collection<Profile> search(ProfileSearch profileSearch) {
        if (log.isDebugEnabled()) {
            log.debug("search(ProfileSearch profileSearch) <-");
            log.debug("profileSearch: " + profileSearch);
        }
        
        Collection<Profile> list = null;
        if (profileSearch == null) {
            list = new ArrayList<Profile>();
        } else {
            String privatePhone = profileSearch.getPrivatePhone();
            User user = profileSearch.getUser();

            if (log.isDebugEnabled()) {
                log.debug("search(ProfileSearch <-");
                log.debug("privatePhone       : " + privatePhone);
                log.debug("user       : " + user);
            }

                Session session = getSession();
                Criteria criteria = session.createCriteria(Profile.class);
            if (privatePhone != null)
                criteria.add(Restrictions.eq("privatePhone", privatePhone));
            if (user != null){
                Criteria subCriteria = criteria.createCriteria("user");
                subCriteria.add(Restrictions.idEq(user.getId()));
            }
            list = criteria.list();

        }


        if (log.isDebugEnabled())
            log.debug("search(ProfileSearch profileSearch) ->");
        return list;
    }

}
