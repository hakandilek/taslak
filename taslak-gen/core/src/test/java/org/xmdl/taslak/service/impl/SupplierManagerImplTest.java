
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
 * Supplier Service Implementation Test
 *  
 * $Id$
 *
 * @generated
 */ 
public class SupplierManagerImplTest extends BaseManagerMockTestCase {

    /**
     * @generated
     */ 
    private SupplierManagerImpl manager = null;

    /**
     * @generated
     */ 
    private Mock dao = null;

    /**
     * @generated
     */ 
    private Supplier supplier = null;

    /**
     * @generated
     */ 
    protected void setUp() throws Exception {
        dao = new Mock(SupplierDAO.class);
        manager = new SupplierManagerImpl((SupplierDAO) dao.proxy());
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
        supplier = new Supplier();

        // set expected behavior on DAO
        dao.expects(once()).method("get")
                .with(eq(id))
                .will(returnValue(supplier));

        Supplier result = manager.get(id);
        assertSame(supplier, result);
    }

    /**
     * @generated
     */ 
    public void testGetAll() {
        log.debug("testing getAll");

        List<Supplier> list = new ArrayList<Supplier>();

        // set expected behavior on dao
        dao.expects(once()).method("getAll")
                .will(returnValue(list));

        List<Supplier> result = manager.getAll();
        assertSame(list, result);
    }

    /**
     * @generated
     */ 
    public void testSearch() {
        log.debug("testing search");

        List<Supplier> list = new ArrayList<Supplier>();
        SupplierSearch searchBean=new SupplierSearch();

        // set expected behavior on DAO
        dao.expects(once()).method("search")
                .with(eq(searchBean))
                .will(returnValue(list));

        Collection<Supplier> result = manager.search(searchBean);
        assertSame(list, result);
    }

    /**
     * @generated
     */ 
    public void testSave() {
        log.debug("testing save");

        supplier = new Supplier();

        // set expected behavior on DAO
        dao.expects(once()).method("save")
                .with(same(supplier))
                .will(returnValue(supplier));

        manager.save(supplier);
    }

    /**
     * @generated
     */ 
    public void testRemove() {
        log.debug("testing remove");

        Long id = 11L;
        supplier = new Supplier();

        // set expected behavior on DAO
        dao.expects(once()).method("remove")
                .with(eq(id))
                .isVoid();

        manager.remove(id);
    }

}
