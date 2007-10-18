package org.xmdl.taslak.dao;

import org.xmdl.ida.lib.dao.GenericDao;
import org.xmdl.taslak.model.Product;
import org.xmdl.taslak.model.search.ProductSearch;

import java.util.Collection;

public interface ProductDao extends GenericDao<Product, Long> {
    Collection<Product> search(String name, Double fromPrice, Double toPrice);
    Collection<Product> search(ProductSearch productSearch);
}
