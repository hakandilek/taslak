 
package org.xmdl.taslak.dao;


import java.util.*;

import org.xmdl.ida.lib.dao.GenericDao;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*; 







/**
 *
 * Profile DAO interface
 *  
 * $Id$
 *
 * @generated
 */ 
public interface ProfileDAO extends GenericDao<Profile, Long> {

    /**
     * @generated
     */ 
    Collection<Profile> search(ProfileSearch searchBean);

}
