package org.xmdl.taslak.dao;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.xmdl.ida.lib.test.BaseDaoTestCase;
import org.xmdl.taslak.model.Product;
import org.xmdl.taslak.model.ProductType;
import org.xmdl.taslak.model.Supplier;
import org.xmdl.taslak.model.search.ProductSearch;

public class ProductDaoTest extends BaseDaoTestCase {
    private ProductDao productDao = null;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void testAddAndRemoveProduct() throws Exception {
        Product product = new Product();
        product.setName("abcd");
        product.setPrice(10d);
        product.setProductType(ProductType.PRODUCT);

        product = productDao.save(product);
        flush();

        product = productDao.get(product.getId());

        assertEquals("abcd", product.getName());
        assertEquals(ProductType.PRODUCT, product.getProductType());
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
    	ProductSearch search = new ProductSearch("abcd",9d,11d,null, null);
        Collection<Product> products = productDao.search(search);
        assertTrue(products.size() > 0);
    }

    public void testSearchWithSupplier() throws Exception {
    	Supplier supplier = new Supplier();
    	supplier.setId(1L);
		ProductSearch search = new ProductSearch(null,null,null,null, supplier);
        Collection<Product> products = productDao.search(search);
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