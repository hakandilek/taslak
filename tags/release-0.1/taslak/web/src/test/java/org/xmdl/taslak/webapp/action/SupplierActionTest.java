package org.xmdl.taslak.webapp.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.xmdl.ida.lib.web.test.BaseActionTestCase;
import org.xmdl.taslak.model.Supplier;
import org.xmdl.taslak.model.search.SupplierSearch;
import org.xmdl.taslak.service.SupplierManager;

import com.opensymphony.xwork2.ActionSupport;

public class SupplierActionTest extends BaseActionTestCase {
    private SupplierAction action;


    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        action = new SupplierAction();
        SupplierManager supplierManager = (SupplierManager) applicationContext.getBean("supplierManager");
        action.setSupplierManager(supplierManager);

        // add a test supplier to the database
        Supplier supplier = new Supplier();

        // enter all required fields
        supplier.setName("fdsslklcs");

        SupplierSearch search = new SupplierSearch();
        action.setSupplierSearch(search);

        supplierManager.save(supplier);
    }

    public void testSearch() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getSuppliers().size() >= 1);
    }

    public void testCopy() throws Exception {
        action.setIdToCopy(1L);
        assertEquals("success", action.copy());
        assertNotNull(action.getSupplier());
        assertNull(action.getSupplier().getId());
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(1L);
        assertNull(action.getSupplier());
        assertEquals("success", action.edit());
        assertNotNull(action.getSupplier());
        assertFalse(action.hasActionErrors());
    }

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

//TODO:this test fails
//    public void testMassDelete() throws Exception {
//        Supplier p = action.getSupplierManager().getAll().get(0);
//
//        List<String> deleteIds = new ArrayList<String>();
//        deleteIds.add(p.getId() + "");
//
//        action.setDeleteId(deleteIds);
//        try {
//            assertEquals("success", action.deleteMass());
//        } catch (DataIntegrityViolationException e) {
//            e.printStackTrace();
//        } catch (ConstraintViolationException e) {
//            e.printStackTrace();
//        }
//    }
}