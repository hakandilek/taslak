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
public class SupplierActionTest extends BaseActionTestCase {
    /**
     * @generated
     */
    private SupplierAction action;


    /**
     * @generated
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        action = new SupplierAction();
        SupplierManager supplierManager = (SupplierManager) applicationContext.getBean("supplierManager");
        action.setSupplierManager(supplierManager);

        // add a test supplier to the database
        Supplier supplier = new Supplier();

        // enter all required fields
        supplier.setName("3zjaE6REs45XNSu");


        supplierManager.save(supplier);
    }

    /**
     * @generated
     */
    public void testSearch() throws Exception {
        SupplierSearch search = new SupplierSearch();
        action.setSupplierSearch(search);

        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getSuppliers().size() >= 1);
    }

    /**
     * @generated
     */
    public void testCopy() throws Exception {
        action.setIdToCopy(1L);
        assertEquals("success", action.copy());
        assertNotNull(action.getSupplier());
        assertNull(action.getSupplier().getId());
    }

    /**
     * @generated
     */
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(1L);
        assertNull(action.getSupplier());
        assertEquals("success", action.edit());
        assertNotNull(action.getSupplier());
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
        assertNotNull(action.getSupplier());

        Supplier supplier = action.getSupplier();
        // update required fields

        action.setSupplier(supplier);

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
        Supplier supplier = new Supplier();
        supplier.setId(2L);
        action.setSupplier(supplier);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }

    /**
     * @generated
     */
    public void testMassDelete() throws Exception {
        Supplier p = action.getSupplierManager().get(new Long(3));

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