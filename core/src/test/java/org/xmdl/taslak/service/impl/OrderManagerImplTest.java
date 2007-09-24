package org.xmdl.taslak.service.impl;

import org.jmock.Mock;
import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.model.search.OrderSearch;
import org.xmdl.taslak.dao.OrderDao;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class OrderManagerImplTest extends BaseManagerMockTestCase {
    private OrderManagerImpl manager = null;
    private Mock dao = null;
    private Order order = null;

    protected void setUp() throws Exception {
        dao = new Mock(OrderDao.class);
        manager = new OrderManagerImpl((OrderDao) dao.proxy());
    }

    protected void tearDown() throws Exception {
        manager = null;
    }

    public void testGetOrder() {
        log.debug("testing getOrder");

        Long id = 7L;
        order = new Order();

        // set expected behavior on dao
        dao.expects(once()).method("get")
                .with(eq(id))
                .will(returnValue(order));

        Order result = manager.get(id);
        assertSame(order, result);
    }

    public void testGetOrders() {
        log.debug("testing getOrders");

        List orders = new ArrayList();

        // set expected behavior on dao
        dao.expects(once()).method("getAll")
                .will(returnValue(orders));

        List result = manager.getAll();
        assertSame(orders, result);
    }

    public void testSearch() {
        log.debug("testing search");

        List people = new ArrayList();
        OrderSearch orderSearch=new OrderSearch();

        // set expected behavior on dao
        dao.expects(once()).method("search")
                .with(eq(orderSearch))
                .will(returnValue(people));

        Collection result = manager.search(orderSearch);
        assertSame(people, result);
    }

    public void testSaveOrder() {
        log.debug("testing saveOrder");

        order = new Order();

        // set expected behavior on dao
        dao.expects(once()).method("save")
                .with(same(order))
                .will(returnValue(order));

        manager.save(order);
    }

    public void testRemoveOrder() {
        log.debug("testing removeOrder");

        Long id = 11L;
        order = new Order();

        // set expected behavior on dao
        dao.expects(once()).method("remove")
                .with(eq(id))
                .isVoid();

        manager.remove(id);
    }
}
