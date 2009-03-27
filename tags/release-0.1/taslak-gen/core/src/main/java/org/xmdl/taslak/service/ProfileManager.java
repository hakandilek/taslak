
package org.xmdl.taslak.service;


import java.util.Collection;

import org.xmdl.ida.lib.service.GenericManager;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*; 







/**
 *
 * Profile Service interface
 *  
 * $Id$
 *
 * @generated
 */ 
public interface ProfileManager extends GenericManager<Profile, Long>{

    /**
     * @generated
     */ 
    Collection<Profile> search(ProfileSearch profileSearch);

}
