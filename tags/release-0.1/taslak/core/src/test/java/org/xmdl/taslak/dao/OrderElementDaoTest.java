package org.xmdl.taslak.dao;

import org.xmdl.ida.lib.test.BaseDaoTestCase;
import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.model.OrderElement;
import org.xmdl.taslak.model.Product;
import org.xmdl.taslak.model.search.OrderElementSearch;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public class OrderElementDaoTest extends BaseDaoTestCase {
    private OrderElementDao orderElementDao = null;
    private ProductDao productDao = null;
    private OrderDao orderDao = null;

    public void setOrderElementDao(OrderElementDao orderElementDao) {
        this.orderElementDao = orderElementDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void testAddAndRemoveOrderElement() throws Exception {
        OrderElement orderElement = new OrderElement();
        orderElement.setQuantity(15L);
        orderElement.setProduct(productDao.getAll().get(0));
        orderElement.setOrder(orderDao.getAll().get(0));

        orderElement = orderElementDao.save(orderElement);
        flush();

        orderElement = orderElementDao.get(orderElement.getId());

        assertEquals(new Long(15), orderElement.getQuantity());
        assertNotNull(orderElement.getId());

        log.debug("removing orderElement...");

        orderElementDao.remove(orderElement.getId());
        flush();

        try {
            orderElementDao.get(orderElement.getId());
            fail("OrderElement found in database");
        } catch (DataAccessException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

    public void testSearch() throws Exception {
    	Order order = orderDao.getAll().get(0);
		Product product = productDao.getAll().get(0);
		OrderElementSearch search = new OrderElementSearch(14L,16L,order, product);
        Collection<OrderElement> orderElements = orderElementDao.search(search);
        assertTrue(orderElements.size() > 0);
    }

    public void testCopyFrom() throws Exception{
        OrderElement orderElement =orderElementDao.getAll().get(0);
        OrderElement o = orderElementDao.copyFrom(orderElement);

        assertEquals(null,o.getId());
        assertEquals(orderElement.getOrder(),o.getOrder());
        assertEquals(orderElement.getProduct(),o.getProduct());
        assertEquals(orderElement.getQuantity(), o.getQuantity());
    }

    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();

        OrderElement orderElement = new OrderElement();
        orderElement.setQuantity(15L);
        orderElement.setProduct(productDao.getAll().get(0));
        orderElement.setOrder(orderDao.getAll().get(0));
        orderElementDao.save(orderElement);
    }
}
