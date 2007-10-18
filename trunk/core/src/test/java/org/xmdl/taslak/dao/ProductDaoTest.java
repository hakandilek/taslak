package org.xmdl.taslak.dao;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.xmdl.ida.lib.test.BaseDaoTestCase;
import org.xmdl.taslak.model.Product;

public class ProductDaoTest extends BaseDaoTestCase {
    private ProductDao productDao = null;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void testAddAndRemoveProduct() throws Exception {
        Product product = new Product();
        product.setName("abcd");
        product.setPrice(10d);

        product = productDao.save(product);
        flush();

        product = productDao.get(product.getId());

        assertEquals("abcd", product.getName());
        assertNotNull(product.getId());

        log.debug("removing product...");

        productDao.remove(product.getId());
        flush();

        try {
            productDao.get(product.getId());
            fail("Product found in database");
        } catch (DataAccessException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

    public void testSearch() throws Exception {
        Collection<Product> products = productDao.search("abcd",9d,11d);
        assertTrue(products.size() > 0);
    }

    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();

        Product product = new Product();
        product.setName("abcd");
        product.setPrice(10d);
        productDao.save(product);
    }
}