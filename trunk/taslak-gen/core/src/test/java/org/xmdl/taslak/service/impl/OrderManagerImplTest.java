
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
 * Order Service Implementation Test
 *  
 * $Id$
 *
 * @generated
 */ 
public class OrderManagerImplTest extends BaseManagerMockTestCase {

    /**
     * @generated
     */ 
    private OrderManagerImpl manager = null;

    /**
     * @generated
     */ 
    private Mock dao = null;

    /**
     * @generated
     */ 
    private Order order = null;

    /**
     * @generated
     */ 
    protected void setUp() throws Exception {
        dao = new Mock(OrderDAO.class);
        manager = new OrderManagerImpl((OrderDAO) dao.proxy());
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
        order = new Order();

        // set expected behavior on DAO
        dao.expects(once()).method("get")
                .with(eq(id))
                .will(returnValue(order));

        Order result = manager.get(id);
        assertSame(order, result);
    }

    /**
     * @generated
     */ 
    public void testGetAll() {
        log.debug("testing getAll");

        List<Order> list = new ArrayList<Order>();

        // set expected behavior on dao
        dao.expects(once()).method("getAll")
                .will(returnValue(list));

        List<Order> result = manager.getAll();
        assertSame(list, result);
    }

    /**
     * @generated
     */ 
    public void testSearch() {
        log.debug("testing search");

        List<Order> list = new ArrayList<Order>();
        OrderSearch searchBean=new OrderSearch();

        // set expected behavior on DAO
        dao.expects(once()).method("search")
                .with(eq(searchBean))
                .will(returnValue(list));

        Collection<Order> result = manager.search(searchBean);
        assertSame(list, result);
    }

    /**
     * @generated
     */ 
    public void testSave() {
        log.debug("testing save");

        order = new Order();

        // set expected behavior on DAO
        dao.expects(once()).method("save")
                .with(same(order))
                .will(returnValue(order));

        manager.save(order);
    }

    /**
     * @generated
     */ 
    public void testRemove() {
        log.debug("testing remove");

        Long id = 11L;
        order = new Order();

        // set expected behavior on DAO
        dao.expects(once()).method("remove")
                .with(eq(id))
                .isVoid();

        manager.remove(id);
    }

}
