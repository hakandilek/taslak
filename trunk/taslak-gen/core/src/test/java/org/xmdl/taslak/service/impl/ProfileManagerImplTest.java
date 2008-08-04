
package org.xmdl.taslak.service.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jmock.Mock;

import org.xmdl.ida.lib.test.BaseManagerMockTestCase;

import org.xmdl.taslak.dao.*;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*; 











/**
 *
 * Profile Service Implementation Test
 *  
 * $Id$
 *
 * @generated
 */ 
public class ProfileManagerImplTest extends BaseManagerMockTestCase {

    /**
     * @generated
     */ 
    private ProfileManagerImpl manager = null;

    /**
     * @generated
     */ 
    private Mock dao = null;

    /**
     * @generated
     */ 
    private Profile profile = null;

    /**
     * @generated
     */ 
    protected void setUp() throws Exception {
        dao = new Mock(ProfileDAO.class);
        manager = new ProfileManagerImpl((ProfileDAO) dao.proxy());
    }

    /**
     * @generated
     */ 
    protected void tearDown() throws Exception {
        manager = null;
    }

    /**
     * @generated
     */ 
    public void testGet() {
        log.debug("testing get");

        Long id = 7L;
        profile = new Profile();

        // set expected behavior on DAO
        dao.expects(once()).method("get")
                .with(eq(id))
                .will(returnValue(profile));

        Profile result = manager.get(id);
        assertSame(profile, result);
    }

    /**
     * @generated
     */ 
    public void testGetAll() {
        log.debug("testing getAll");

        List<Profile> list = new ArrayList<Profile>();

        // set expected behavior on dao
        dao.expects(once()).method("getAll")
                .will(returnValue(list));

        List<Profile> result = manager.getAll();
        assertSame(list, result);
    }

    /**
     * @generated
     */ 
    public void testSearch() {
        log.debug("testing search");

        List<Profile> list = new ArrayList<Profile>();
        ProfileSearch searchBean=new ProfileSearch();

        // set expected behavior on DAO
        dao.expects(once()).method("search")
                .with(eq(searchBean))
                .will(returnValue(list));

        Collection<Profile> result = manager.search(searchBean);
        assertSame(list, result);
    }

    /**
     * @generated
     */ 
    public void testSave() {
        log.debug("testing save");

        profile = new Profile();

        // set expected behavior on DAO
        dao.expects(once()).method("save")
                .with(same(profile))
                .will(returnValue(profile));

        manager.save(profile);
    }

    /**
     * @generated
     */ 
    public void testRemove() {
        log.debug("testing remove");

        Long id = 11L;
        profile = new Profile();

        // set expected behavior on DAO
        dao.expects(once()).method("remove")
                .with(eq(id))
                .isVoid();

        manager.remove(id);
    }

}
