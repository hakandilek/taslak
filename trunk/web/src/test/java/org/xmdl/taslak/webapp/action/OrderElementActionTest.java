package org.xmdl.taslak.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.xmdl.taslak.service.GenericManager;
import org.xmdl.taslak.model.OrderElement;
import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.model.Product;
import org.springframework.mock.web.MockHttpServletRequest;

public class OrderElementActionTest extends BaseActionTestCase {
    private OrderElementAction action;

    @Override @SuppressWarnings("unchecked")
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        action = new OrderElementAction();
        GenericManager orderElementManager = (GenericManager) applicationContext.getBean("orderElementManager");
        GenericManager productManager = (GenericManager) applicationContext.getBean("productManager");
        GenericManager orderManager = (GenericManager) applicationContext.getBean("orderManager");
        action.setOrderElementManager(orderElementManager);

        // add a test orderElement to the database
        OrderElement orderElement = new OrderElement();

        // enter all required fields
        orderElement.setQuantity(446232345652l);
        orderElement.setProduct((Product) productManager.getAll().get(0));
        orderElement.setOrder((Order) orderManager.getAll().get(0));

        orderElementManager.save(orderElement);
    }

    public void testSearch() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getOrderElements().size() >= 1);
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(-1L);
        assertNull(action.getOrderElement());
        assertEquals("success", action.edit());
        assertNotNull(action.getOrderElement());
        assertFalse(action.hasActionErrors());
    }

    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(-1L);
        assertEquals("success", action.edit());
        assertNotNull(action.getOrderElement());

        OrderElement orderElement = action.getOrderElement();
        // update required fields
        orderElement.setQuantity(446232345652l);

        action.setOrderElement(orderElement);

        assertEquals("input", action.save());
        assertFalse(action.hasActionErrors());
        assertFalse(action.hasFieldErrors());
        assertNotNull(request.getSession().getAttribute("messages"));
    }

    public void testRemove() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setDelete("");
        OrderElement orderElement = new OrderElement();
        orderElement.setId(-2L);
        action.setOrderElement(orderElement);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }
}