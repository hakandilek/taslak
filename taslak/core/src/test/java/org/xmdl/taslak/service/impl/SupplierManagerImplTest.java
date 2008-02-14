package org.xmdl.taslak.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jmock.Mock;
import org.xmdl.ida.lib.test.BaseManagerMockTestCase;
import org.xmdl.taslak.dao.SupplierDao;
import org.xmdl.taslak.model.Supplier;
import org.xmdl.taslak.model.search.SupplierSearch;

public class SupplierManagerImplTest extends BaseManagerMockTestCase {
    private SupplierManagerImpl manager = null;
    private Mock dao = null;
    private Supplier supplier = null;

    protected void setUp() throws Exception {
        dao = new Mock(SupplierDao.class);
        manager = new SupplierManagerImpl((SupplierDao) dao.proxy());
    }

    protected void tearDown() throws Exception {
        manager = null;
    }

    public void testGet() {
        log.debug("testing getSupplier");

        Long id = 7L;
        supplier = new Supplier();

        // set expected behavior on dao
        dao.expects(once()).method("get")
                .with(eq(id))
                .will(returnValue(supplier));

        Supplier result = manager.get(id);
        assertSame(supplier, result);
    }

    public void testGetAll() {
        log.debug("testing getSuppliers");

        List<Supplier> list = new ArrayList<Supplier>();

        // set expected behavior on dao
        dao.expects(once()).method("getAll")
                .will(returnValue(list));

        List<Supplier> result = manager.getAll();
        assertSame(list, result);
    }

    public void testSearch() {
        log.debug("testing search");

        List<Supplier> list = new ArrayList<Supplier>();
        SupplierSearch searchBean=new SupplierSearch();

        // set expected behavior on dao
        dao.expects(once()).method("search")
                .with(eq(searchBean))
                .will(returnValue(list));

        Collection<Supplier> result = manager.search(searchBean);
        assertSame(list, result);
    }

    public void testSave() {
        log.debug("testing saveSupplier");

        supplier = new Supplier();

        // set expected behavior on dao
        dao.expects(once()).method("save")
                .with(same(supplier))
                .will(returnValue(supplier));

        manager.save(supplier);
    }

    public void testRemove() {
        log.debug("testing removeSupplier");

        Long id = 11L;
        supplier = new Supplier();

        // set expected behavior on dao
        dao.expects(once()).method("remove")
                .with(eq(id))
                .isVoid();

        manager.remove(id);
    }
}