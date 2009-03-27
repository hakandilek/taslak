
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
 * Product Service Implementation Test
 *  
 * $Id$
 *
 * @generated
 */ 
public class ProductManagerImplTest extends BaseManagerMockTestCase {

    /**
     * @generated
     */ 
    private ProductManagerImpl manager = null;

    /**
     * @generated
     */ 
    private Mock dao = null;

    /**
     * @generated
     */ 
    private Product product = null;

    /**
     * @generated
     */ 
    protected void setUp() throws Exception {
        dao = new Mock(ProductDAO.class);
        manager = new ProductManagerImpl((ProductDAO) dao.proxy());
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
        product = new Product();

        // set expected behavior on DAO
        dao.expects(once()).method("get")
                .with(eq(id))
                .will(returnValue(product));

        Product result = manager.get(id);
        assertSame(product, result);
    }

    /**
     * @generated
     */ 
    public void testGetAll() {
        log.debug("testing getAll");

        List<Product> list = new ArrayList<Product>();

        // set expected behavior on dao
        dao.expects(once()).method("getAll")
                .will(returnValue(list));

        List<Product> result = manager.getAll();
        assertSame(list, result);
    }

    /**
     * @generated
     */ 
    public void testSearch() {
        log.debug("testing search");

        List<Product> list = new ArrayList<Product>();
        ProductSearch searchBean=new ProductSearch();

        // set expected behavior on DAO
        dao.expects(once()).method("search")
                .with(eq(searchBean))
                .will(returnValue(list));

        Collection<Product> result = manager.search(searchBean);
        assertSame(list, result);
    }

    /**
     * @generated
     */ 
    public void testSave() {
        log.debug("testing save");

        product = new Product();

        // set expected behavior on DAO
        dao.expects(once()).method("save")
                .with(same(product))
                .will(returnValue(product));

        manager.save(product);
    }

    /**
     * @generated
     */ 
    public void testRemove() {
        log.debug("testing remove");

        Long id = 11L;
        product = new Product();

        // set expected behavior on DAO
        dao.expects(once()).method("remove")
                .with(eq(id))
                .isVoid();

        manager.remove(id);
    }

}
