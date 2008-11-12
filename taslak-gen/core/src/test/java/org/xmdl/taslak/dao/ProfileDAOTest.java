
package org.xmdl.taslak.dao;


import java.util.*;

import org.springframework.dao.DataAccessException;

import org.xmdl.ida.lib.test.BaseDaoTestCase;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*; 








/**
 *
 * Profile DAO Test Case
 *  
 * $Id$
 *
 * @generated
 */ 
public class ProfileDAOTest extends BaseDaoTestCase {

    /**
     * @generated
     */ 
    private ProfileDAO profileDAO = null;

    /**
     * @generated
     */ 
    public void setProfileDAO(ProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
    }

    /**
     * @generated
     */ 
    public void testAddAndRemoveProfile() throws Exception {
        Profile profile = new Profile();

        profile.setPrivatePhone("4E4PkxyYQZy KJd");

        profile = profileDAO.save(profile);
        flush();

        profile = profileDAO.get(profile.getId());

        assertNotNull(profile.getId());
        assertEquals("4E4PkxyYQZy KJd", profile.getPrivatePhone());

        log.debug("removing profile...");
        profileDAO.remove(profile.getId());
        flush();

        try {
            profileDAO.get(profile.getId());
            fail("Profile found in database");
        } catch (DataAccessException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

    /**
     * @generated
     */ 
    public void testSearch() throws Exception {
        ProfileSearch search = new ProfileSearch();
        search.setPrivatePhone("4E4PkxyYQZy KJd");
        Collection<Profile> profiles = profileDAO.search(search);
        assertTrue(profiles.size() > 0);
    }

    /**
     * @generated
     */ 
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();

        Profile profile = new Profile();
        profile.setPrivatePhone("4E4PkxyYQZy KJd");
        profileDAO.save(profile);
    }

}
