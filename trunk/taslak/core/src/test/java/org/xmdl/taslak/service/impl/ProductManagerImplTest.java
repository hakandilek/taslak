package org.xmdl.taslak.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import org.jmock.Mock;
import org.xmdl.ida.lib.test.BaseManagerMockTestCase;
import org.xmdl.taslak.service.impl.ProductManagerImpl;
import org.xmdl.taslak.model.Product;
import org.xmdl.taslak.model.search.ProductSearch;
import org.xmdl.taslak.dao.ProductDao;

public class ProductManagerImplTest extends BaseManagerMockTestCase {
    private ProductManagerImpl manager = null;
    private Mock dao = null;
    private Product product = null;

    protected void setUp() throws Exception {
        dao = new Mock(ProductDao.class);
        manager = new ProductManagerImpl((ProductDao) dao.proxy());
    }

    protected void tearDown() throws Exception {
        manager = null;
    }

    public void testGet() {
        log.debug("testing getProduct");

        Long id = 7L;
        product = new Product();

        // set expected behavior on dao
        dao.expects(once()).method("get")
                .with(eq(id))
                .will(returnValue(product));

        Product result = manager.get(id);
        assertSame(product, result);
    }

    public void testGetAll() {
        log.debug("testing getProducts");

        List<Product> list = new ArrayList<Product>();

        // set expected behavior on dao
        dao.expects(once()).method("getAll")
                .will(returnValue(list));

        List<Product> result = manager.getAll();
        assertSame(list, result);
    }

    public void testSearch() {
        log.debug("testing search");

        List<Product> list = new ArrayList<Product>();
        ProductSearch searchBean=new ProductSearch();

        // set expected behavior on dao
        dao.expects(once()).method("search")
                .with(eq(searchBean))
                .will(returnValue(list));

        Collection<Product> result = manager.search(searchBean);
        assertSame(list, result);
    }

    public void testSave() {
        log.debug("testing saveProduct");

        product = new Product();

        // set expected behavior on dao
        dao.expects(once()).method("save")
                .with(same(product))
                .will(returnValue(product));

        manager.save(product);
    }

    public void testRemove() {
        log.debug("testing removeProduct");

        Long id = 11L;
        product = new Product();

        // set expected behavior on dao
        dao.expects(once()).method("remove")
                .with(eq(id))
                .isVoid();

        manager.remove(id);
    }
}