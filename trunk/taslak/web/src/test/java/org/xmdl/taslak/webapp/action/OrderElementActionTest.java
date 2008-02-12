package org.xmdl.taslak.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.xmdl.ida.lib.web.test.BaseActionTestCase;
import org.xmdl.taslak.service.OrderManager;
import org.xmdl.taslak.service.ProductManager;
import org.xmdl.taslak.service.OrderElementManager;
import org.xmdl.taslak.model.OrderElement;
import org.xmdl.taslak.model.search.OrderElementSearch;
import org.springframework.mock.web.MockHttpServletRequest;

public class OrderElementActionTest extends BaseActionTestCase {
    private OrderElementAction action;

    @Override @SuppressWarnings("unchecked")
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        action = new OrderElementAction();
        OrderElementManager orderElementManager = (OrderElementManager) applicationContext.getBean("orderElementManager");
        ProductManager productManager = (ProductManager) applicationContext.getBean("productManager");
        OrderManager orderManager = (OrderManager) applicationContext.getBean("orderManager");
        action.setOrderElementManager(orderElementManager);
        action.setOrderManager(orderManager);
        action.setProductManager(productManager);

        // add a test orderElement to the database
        OrderElement orderElement = new OrderElement();

        // enter all required fields
        orderElement.setQuantity(446232345652l);
        orderElement.setProduct(productManager.getAll().get(0));
        orderElement.setOrder(orderManager.getAll().get(0));

        OrderElementSearch search = new OrderElementSearch();
        action.setOrderElementSearch(search);

        orderElementManager.save(orderElement);
    }

    public void testSearch() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getOrderElements().size() >= 1);
    }

    public void testCopy() throws Exception{
        action.setIdToCopy(1L);
        assertEquals("success",action.copy());
        assertNotNull(action.getOrderElement());
        assertNull(action.getOrderElement().getId());
    }


    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(1L);
        assertNull(action.getOrderElement());
        assertEquals("success", action.edit());
        assertNotNull(action.getOrderElement());
        assertFalse(action.hasActionErrors());
    }

    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(1L);
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
        orderElement.setId(2L);
        action.setOrderElement(orderElement);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }
}