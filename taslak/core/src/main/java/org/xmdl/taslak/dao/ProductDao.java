package org.xmdl.taslak.dao;

import java.util.Collection;

import org.xmdl.ida.lib.dao.GenericDao;
import org.xmdl.taslak.model.Product;
import org.xmdl.taslak.model.search.ProductSearch;

public interface ProductDao extends GenericDao<Product, Long> {
    Collection<Product> search(ProductSearch productSearch);
}
