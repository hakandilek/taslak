package org.xmdl.taslak.service;

import java.util.Collection;

import org.xmdl.ida.lib.service.GenericManager;
import org.xmdl.taslak.model.Supplier;
import org.xmdl.taslak.model.search.SupplierSearch;

public interface SupplierManager extends GenericManager<Supplier,Long>{
    Collection<Supplier> search(SupplierSearch search);
}
