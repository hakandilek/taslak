
package org.xmdl.taslak.service.impl;


import java.util.Collection;

import org.xmdl.ida.lib.service.impl.GenericManagerImpl;

import org.xmdl.taslak.dao.*;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*;

import org.xmdl.taslak.service.*; 









/**
 *
 * Profile Service implementation
 *  
 * $Id$
 *
 * @generated
 */ 
public class ProfileManagerImpl extends GenericManagerImpl<Profile, Long> implements ProfileManager {

    /**
     * @generated
     */ 
    private final ProfileDAO profileDAO;

    /**
     * @generated
     */ 
    public ProfileManagerImpl(ProfileDAO profileDAO) {
        super(profileDAO);
        this.profileDAO = profileDAO;
    }

    /**
     * @generated
     */ 
    public Collection<Profile> search(ProfileSearch profileSearch) {
        return profileDAO.search(profileSearch);
    }


}
