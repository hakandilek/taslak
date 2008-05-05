
package org.xmdl.taslak.dao;


import java.util.*;

import org.springframework.dao.DataAccessException;

import org.xmdl.ida.lib.test.BaseDaoTestCase;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*; 








/**
 *
 * Product DAO Test Case
 *  
 * $Id$
 *
 * @generated
 */ 
public class ProductDAOTest extends BaseDaoTestCase {

    /**
     * @generated
     */ 
    private ProductDAO productDAO = null;

    /**
     * @generated
     */ 
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    /**
     * @generated
     */ 
    public void testAddAndRemoveProduct() throws Exception {
        Product product = new Product();

        product.setName("FAPJP5zsxfbPh4k");
        product.setPrice(new Double(0.37977096517272835));

        product = productDAO.save(product);
        flush();

        product = productDAO.get(product.getId());

        assertNotNull(product.getId());
        assertEquals("FAPJP5zsxfbPh4k", product.getName());
        assertEquals(new Double(0.37977096517272835), product.getPrice());

        log.debug("removing product...");
        productDAO.remove(product.getId());
        flush();

        try {
            productDAO.get(product.getId());
            fail("Product found in database");
        } catch (DataAccessException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

    /**
     * @generated
     */ 
    public void testSearch() throws Exception {
        ProductSearch search = new ProductSearch();
        search.setName("FAPJP5zsxfbPh4k");
        search.setPriceMin(new Double(0.37977096517272835));
        search.setPriceMax(new Double(0.37977096517272835));
        Collection<Product> products = productDAO.search(search);
        assertTrue(products.size() > 0);
    }

    /**
     * @generated
     */ 
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();

        Product product = new Product();
        product.setName("FAPJP5zsxfbPh4k");
        product.setPrice(new Double(0.37977096517272835));
        productDAO.save(product);
    }

}
