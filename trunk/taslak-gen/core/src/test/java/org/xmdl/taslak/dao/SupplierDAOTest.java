
package org.xmdl.taslak.dao;


import java.util.*;

import org.springframework.dao.DataAccessException;

import org.xmdl.ida.lib.test.BaseDaoTestCase;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*; 








/**
 *
 * Supplier DAO Test Case
 *  
 * $Id$
 *
 * @generated
 */ 
public class SupplierDAOTest extends BaseDaoTestCase {

    /**
     * @generated
     */ 
    private SupplierDAO supplierDAO = null;

    /**
     * @generated
     */ 
    public void setSupplierDAO(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    /**
     * @generated
     */ 
    public void testAddAndRemoveSupplier() throws Exception {
        Supplier supplier = new Supplier();

        supplier.setName("WcDfbVfHYrv PQf");
        Contact contact = new Contact();
        contact.setFullname("2jnLZE WGG24QzW");
        contact.setPhone("VZEwM7Nxu ykb P");
        supplier.setContact(contact);

        supplier = supplierDAO.save(supplier);
        flush();

        supplier = supplierDAO.get(supplier.getId());

        assertNotNull(supplier.getId());
        assertEquals("WcDfbVfHYrv PQf", supplier.getName());

        log.debug("removing supplier...");
        supplierDAO.remove(supplier.getId());
        flush();

        try {
            supplierDAO.get(supplier.getId());
            fail("Supplier found in database");
        } catch (DataAccessException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

    /**
     * @generated
     */ 
    public void testSearch() throws Exception {
        SupplierSearch search = new SupplierSearch();
        search.setName("WcDfbVfHYrv PQf");
        Collection<Supplier> suppliers = supplierDAO.search(search);
        assertTrue(suppliers.size() > 0);
    }

    /**
     * @generated
     */ 
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();

        Supplier supplier = new Supplier();
        supplier.setName("WcDfbVfHYrv PQf");
        Contact contact = new Contact();
        contact.setFullname("2jnLZE WGG24QzW");
        contact.setPhone("VZEwM7Nxu ykb P");
        supplier.setContact(contact);
        supplierDAO.save(supplier);
    }

}
