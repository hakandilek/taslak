package org.xmdl.taslak.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.xmdl.taslak.service.ProductManager;
import org.xmdl.taslak.model.Product;
import org.xmdl.taslak.model.search.ProductSearch;
import org.springframework.mock.web.MockHttpServletRequest;

public class ProductActionTest extends BaseActionTestCase {
    private ProductAction action;

    @Override @SuppressWarnings("unchecked")
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        action = new ProductAction();
        ProductManager productManager = (ProductManager) applicationContext.getBean("productManager");
        action.setProductManager(productManager);
    
        // add a test product to the database
        Product product = new Product();

        // enter all required fields
        product.setName("fdsslklcs");

        ProductSearch search = new ProductSearch();
        action.setProductSearch(search);

        productManager.save(product);
    }

    public void testSearch() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getProducts().size() >= 1);
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(-1L);
        assertNull(action.getProduct());
        assertEquals("success", action.edit());
        assertNotNull(action.getProduct());
        assertFalse(action.hasActionErrors());
    }

    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(-1L);
        assertEquals("success", action.edit());
        assertNotNull(action.getProduct());

        Product product = action.getProduct();
        // update required fields

        action.setProduct(product);

        assertEquals("input", action.save());
        assertFalse(action.hasActionErrors());
        assertFalse(action.hasFieldErrors());
        assertNotNull(request.getSession().getAttribute("messages"));
    }

    public void testRemove() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setDelete("");
        Product product = new Product();
        product.setId(-2L);
        action.setProduct(product);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }
}