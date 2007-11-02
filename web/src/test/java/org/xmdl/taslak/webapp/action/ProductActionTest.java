package org.xmdl.taslak.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.xmdl.ida.lib.web.test.BaseActionTestCase;
import org.xmdl.taslak.service.ProductManager;
import org.xmdl.taslak.model.Product;
import org.xmdl.taslak.model.ProductType;
import org.xmdl.taslak.model.search.ProductSearch;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;
import java.util.ArrayList;

public class ProductActionTest extends BaseActionTestCase {
    private ProductAction action;

    private Integer[] productTypeIds = new Integer[]{
            ProductType.PRODUCT.getValue()
    };

    @Override
    @SuppressWarnings("unchecked")
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        action = new ProductAction();
        ProductManager productManager = (ProductManager) applicationContext.getBean("productManager");
        action.setProductManager(productManager);

        // add a test product to the database
        Product product = new Product();

        // enter all required fields
        product.setName("fdsslklcs");
        product.setProductType(ProductType.PRODUCT);

        ProductSearch search = new ProductSearch();
        action.setProductSearch(search);

        action.setProductTypeIds(productTypeIds);

        productManager.save(product);
    }

    public void testSearch() throws Exception {
        action.setProductTypeIds(null);
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getProducts().size() >= 1);
        action.setProductTypeIds(productTypeIds);
    }

    public void testCopy() throws Exception {
        action.setIdToCopy(1L);
        assertEquals("success", action.copy());
        assertNotNull(action.getProduct());
        assertNull(action.getProduct().getId());
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(1L);
        assertNull(action.getProduct());
        assertEquals("success", action.edit());
        assertNotNull(action.getProduct());
        assertFalse(action.hasActionErrors());
    }

    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(1L);
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
        product.setId(2L);
        action.setProduct(product);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }

    public void testMassDelete() throws Exception {
        Product p = action.getProductManager().getAll().get(0);

        List<String> deleteIds = new ArrayList<String>();
        deleteIds.add(p.getId() + "");

        action.setDeleteId(deleteIds);
        try {
            assertEquals("success", action.deleteMass());
            assertTrue(action.hasActionMessages());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            assertTrue(action.hasErrors());
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
    }
}