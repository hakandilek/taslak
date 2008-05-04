package org.xmdl.taslak.webapp.action;


import com.opensymphony.xwork2.ActionSupport;

import java.util.*;

import org.apache.struts2.ServletActionContext;

import org.hibernate.exception.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.mock.web.MockHttpServletRequest;

import org.xmdl.ida.lib.web.test.BaseActionTestCase;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*;

import org.xmdl.taslak.service.*; 












/**
 * 
 * @author Hakan Dilek
 * 
 * @generated
 */
public class ProductActionTest extends BaseActionTestCase {
    /**
     * @generated
     */
    private ProductAction action;


    /**
     * @generated
     */
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
        product.setName("Bfvs6f5vgMBKh7V");
        product.setPrice(new Double(0.462246062829789));


        productManager.save(product);
    }

    /**
     * @generated
     */
    public void testSearch() throws Exception {
        ProductSearch search = new ProductSearch();
        action.setProductSearch(search);

        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getProducts().size() >= 1);
    }

    /**
     * @generated
     */
    public void testCopy() throws Exception {
        action.setIdToCopy(1L);
        assertEquals("success", action.copy());
        assertNotNull(action.getProduct());
        assertNull(action.getProduct().getId());
    }

    /**
     * @generated
     */
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(1L);
        assertNull(action.getProduct());
        assertEquals("success", action.edit());
        assertNotNull(action.getProduct());
        assertFalse(action.hasActionErrors());
    }

    /**
     * @generated
     */
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

    /**
     * @generated
     */
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

    /**
     * @generated
     */
    public void testMassDelete() throws Exception {
        Product p = action.getProductManager().get(new Long(3));

        List<String> deleteIds = new ArrayList<String>();
        deleteIds.add(p.getId() + "");

        action.setDeleteId(deleteIds);
        try {
            assertEquals("success", action.deleteMass());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
    }
}