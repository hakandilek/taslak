package org.xmdl.taslak.dao;


import java.util.*;
import org.springframework.dao.DataAccessException;
import org.xmdl.ida.lib.test.BaseDaoTestCase;
import org.xmdl.taslak.model.*;
import org.xmdl.taslak.model.search.*;

/**
 *
 * Order DAO Test Case
 *  
 * $Id$
 *
 * @generated
 */ 
public class OrderDAOTest extends BaseDaoTestCase {

    /**
     * @generated
     */ 
    private OrderDAO orderDAO = null;

    /**
     * @generated
     */ 
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    /**
     * @generated
     */ 
    public void testAddAndRemoveOrder() throws Exception {
        Order order = new Order();

        order.setName("GBaHRE3Q zHMcsB");
        order.setPriceTotals(new Double(0.03335887159986095));
        order.setCreateDate(new Date(1238308770894L));

        order = orderDAO.save(order);
        flush();

        order = orderDAO.get(order.getId());

        assertNotNull(order.getId());
        assertEquals("GBaHRE3Q zHMcsB", order.getName());
        assertEquals(new Double(0.03335887159986095), order.getPriceTotals());
        assertEquals(new Date(1238308770894L), order.getCreateDate());

        log.debug("removing order...");
        orderDAO.remove(order.getId());
        flush();

        try {
            orderDAO.get(order.getId());
            fail("Order found in database");
        } catch (DataAccessException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

    /**
     * @generated
     */ 
    public void testSearch() throws Exception {
        OrderSearch search = new OrderSearch();
        search.setName("GBaHRE3Q zHMcsB");
        search.setPriceTotalsMin(new Double(0.03335887159986095));
        search.setPriceTotalsMax(new Double(0.03335887159986095));
        search.setCreateDateMin(new Date(1238308770894L));
        search.setCreateDateMax(new Date(1238308770894L));
        Collection<Order> orders = orderDAO.search(search);
        assertTrue(orders.size() > 0);
    }

    /**
     * @generated
     */ 
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();

        Order order = new Order();
        order.setName("GBaHRE3Q zHMcsB");
        order.setPriceTotals(new Double(0.03335887159986095));
        order.setCreateDate(new Date(1238308770894L));
        orderDAO.save(order);
    }

}