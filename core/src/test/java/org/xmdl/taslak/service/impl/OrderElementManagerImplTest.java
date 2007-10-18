package org.xmdl.taslak.service.impl;

import org.jmock.Mock;
import org.xmdl.ida.lib.test.BaseManagerMockTestCase;
import org.xmdl.taslak.model.OrderElement;
import org.xmdl.taslak.model.search.OrderElementSearch;
import org.xmdl.taslak.dao.OrderElementDao;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class OrderElementManagerImplTest extends BaseManagerMockTestCase {
    private OrderElementManagerImpl manager = null;
    private Mock dao = null;
    private OrderElement orderElement = null;

    protected void setUp() throws Exception {
        dao = new Mock(OrderElementDao.class);
        manager = new OrderElementManagerImpl((OrderElementDao) dao.proxy());
    }

    protected void tearDown() throws Exception {
        manager = null;
    }

    public void testGetOrderElement() {
        log.debug("testing getOrderElement");

        Long id = 7L;
        orderElement = new OrderElement();

        // set expected behavior on dao
        dao.expects(once()).method("get")
                .with(eq(id))
                .will(returnValue(orderElement));

        OrderElement result = manager.get(id);
        assertSame(orderElement, result);
    }

    public void testGetOrderElements() {
        log.debug("testing getOrderElements");

        List orderElements = new ArrayList();

        // set expected behavior on dao
        dao.expects(once()).method("getAll")
                .will(returnValue(orderElements));

        List result = manager.getAll();
        assertSame(orderElements, result);
    }

    public void testSearch() {
        log.debug("testing search");

        List people = new ArrayList();
        OrderElementSearch orderElementSearch=new OrderElementSearch();

        // set expected behavior on dao
        dao.expects(once()).method("search")
                .with(eq(orderElementSearch))
                .will(returnValue(people));

        Collection result = manager.search(orderElementSearch);
        assertSame(people, result);
    }

    public void testSaveOrderElement() {
        log.debug("testing saveOrderElement");

        orderElement = new OrderElement();

        // set expected behavior on dao
        dao.expects(once()).method("save")
                .with(same(orderElement))
                .will(returnValue(orderElement));

        manager.save(orderElement);
    }

    public void testRemoveOrderElement() {
        log.debug("testing removeOrderElement");

        Long id = 11L;
        orderElement = new OrderElement();

        // set expected behavior on dao
        dao.expects(once()).method("remove")
                .with(eq(id))
                .isVoid();

        manager.remove(id);
    }
}
