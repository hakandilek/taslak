package org.xmdl.taslak.webapp.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.xmdl.ida.lib.web.test.BaseActionTestCase;
import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.model.search.OrderSearch;
import org.xmdl.taslak.service.OrderManager;

import com.opensymphony.xwork2.ActionSupport;

public class OrderActionTest extends BaseActionTestCase {
    private OrderAction action;

    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        action = new OrderAction();
        OrderManager orderManager = (OrderManager) applicationContext.getBean("orderManager");
        action.setOrderManager(orderManager);

        // add a test order to the database
        Order order = new Order();

        // enter all required fields
        order.setName("DkHxYeWmSc");
        order.setPriceTotals(7.267920169061941E307);
        order.setCreateDate(new java.util.Date());

        OrderSearch search = new OrderSearch();
        action.setOrderSearch(search);

        orderManager.save(order);
    }

    public void testSearch() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getOrders().size() >= 1);
    }

    public void testCopy() throws Exception{
        action.setIdToCopy(1L);
        assertEquals("success",action.copy());
        assertNotNull(action.getOrder());
        assertNull(action.getOrder().getId());
    }


    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(1L);
        assertNull(action.getOrder());
        assertEquals("success", action.edit());
        assertNotNull(action.getOrder());
        assertFalse(action.hasActionErrors());
    }

    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(1L);
        assertEquals("success", action.edit());
        assertNotNull(action.getOrder());

        Order order = action.getOrder();
        // update required fields
        order.setName("YhGaWtQsGh");
        order.setPriceTotals(1.8184039334937247E307);
        order.setCreateDate(new java.util.Date());

        action.setOrder(order);

        assertEquals("input", action.save());
        assertFalse(action.hasActionErrors());
        assertFalse(action.hasFieldErrors());
        assertNotNull(request.getSession().getAttribute("messages"));
    }

    public void testRemove() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setDelete("");
        Order order = new Order();
        order.setId(2L);
        action.setOrder(order);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }

    public void testMassDelete() throws Exception{
        Order o= action.getOrderManager().getAll().get(0);

        List<String> deleteIds = new ArrayList<String>();
        deleteIds.add(o.getId()+"");

        action.setDeleteId(deleteIds);
        try {
            assertEquals("success",action.deleteMass());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
        } catch(ConstraintViolationException e){
            e.printStackTrace();
        }
    }
}