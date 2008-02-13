package org.xmdl.taslak.dao;

import org.xmdl.ida.lib.test.BaseDaoTestCase;
import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.model.search.OrderSearch;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class OrderDaoTest extends BaseDaoTestCase {
    private OrderDao orderDao = null;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void testAddAndRemoveOrder() throws Exception {
        Order order = new Order();
        order.setName("abcd");
        order.setPriceTotals(10d);
        order.setCreateDate(new Date());

        order = orderDao.save(order);
        flush();

        order = orderDao.get(order.getId());

        assertEquals("abcd", order.getName());
        assertNotNull(order.getId());

        log.debug("removing order...");

        orderDao.remove(order.getId());
        flush();

        try {
            orderDao.get(order.getId());
            fail("Order found in database");
        } catch (DataAccessException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

    public void testSearch() throws Exception {
        Calendar cal = GregorianCalendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,-1);
        Date yesterday = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH,2);
        Date tomorrow= cal.getTime();
        OrderSearch search = new OrderSearch("abcd",9d,11d,yesterday,tomorrow);
        Collection<Order> orders = orderDao.search(search);
        assertTrue(orders.size() > 0);
    }

    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();

        Order order = new Order();
        order.setName("abcd");
        order.setPriceTotals(10d);
        order.setCreateDate(new Date());
        orderDao.save(order);
    }
}
