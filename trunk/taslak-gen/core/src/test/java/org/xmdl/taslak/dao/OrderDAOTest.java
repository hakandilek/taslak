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

        order.setName("3zfM Nt9XXmKC8L");
        order.setPriceTotals(new Double(0.7989230725893522));
        order.setCreateDate(new Date(1123068212088L));

        order = orderDAO.save(order);
        flush();

        order = orderDAO.get(order.getId());

        assertNotNull(order.getId());
        assertEquals("3zfM Nt9XXmKC8L", order.getName());
        assertEquals(new Double(0.7989230725893522), order.getPriceTotals());
        assertEquals(new Date(1123068212088L), order.getCreateDate());

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
        search.setName("3zfM Nt9XXmKC8L");
        search.setPriceTotalsMin(new Double(0.7989230725893522));
        search.setPriceTotalsMax(new Double(0.7989230725893522));
        search.setCreateDateMin(new Date(1123068212088L));
        search.setCreateDateMax(new Date(1123068212088L));
        Collection<Order> orders = orderDAO.search(search);
        assertTrue(orders.size() > 0);
    }

    /**
     * @generated
     */ 
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();

        Order order = new Order();
        order.setName("3zfM Nt9XXmKC8L");
        order.setPriceTotals(new Double(0.7989230725893522));
        order.setCreateDate(new Date(1123068212088L));
        orderDAO.save(order);
    }

}