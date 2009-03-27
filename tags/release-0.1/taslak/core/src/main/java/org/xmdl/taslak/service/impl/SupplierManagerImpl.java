package org.xmdl.taslak.service.impl;

import org.xmdl.ida.lib.service.impl.GenericManagerImpl;
import org.xmdl.taslak.model.Supplier;
import org.xmdl.taslak.model.search.SupplierSearch;
import org.xmdl.taslak.service.SupplierManager;
import org.xmdl.taslak.dao.SupplierDao;

import java.util.Collection;

public class SupplierManagerImpl extends GenericManagerImpl<Supplier, Long> implements SupplierManager {

    SupplierDao supplierDao;

    public SupplierManagerImpl(SupplierDao supplierDao) {
        super(supplierDao);
        this.supplierDao = supplierDao;
    }

    public Collection<Supplier> search(SupplierSearch supplierSearch) {
        return supplierDao.search(supplierSearch);
    }
}
