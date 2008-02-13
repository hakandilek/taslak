package org.xmdl.taslak.dao;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.xmdl.ida.lib.test.BaseDaoTestCase;
import org.xmdl.taslak.model.Product;
import org.xmdl.taslak.model.Supplier;
import org.xmdl.taslak.model.search.SupplierSearch;

public class SupplierDaoTest extends BaseDaoTestCase {
    private SupplierDao supplierDao = null;

    public void setSupplierDao(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    public void testAddAndRemoveSupplier() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("abcd");

        supplier = supplierDao.save(supplier);
        flush();

        supplier = supplierDao.get(supplier.getId());

        assertEquals("abcd", supplier.getName());
        assertNotNull(supplier.getId());

        log.debug("removing supplier...");

        supplierDao.remove(supplier.getId());
        flush();

        try {
            supplierDao.get(supplier.getId());
            fail("Supplier found in database");
        } catch (DataAccessException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

    public void testSearch() throws Exception {
    	SupplierSearch search = new SupplierSearch("abcd",null);
        Collection<Supplier> suppliers = supplierDao.search(search);
        assertTrue(suppliers.size() > 0);
    }

    public void testSearchWithProduct() throws Exception {
    	Product product = new Product();
    	product.setId(1L);
		SupplierSearch search = new SupplierSearch(null,product);
        Collection<Supplier> suppliers = supplierDao.search(search);
        assertTrue(suppliers.size() > 0);
    }

    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();

        Supplier supplier = new Supplier();
        supplier.setName("abcd");
        supplierDao.save(supplier);
    }
}