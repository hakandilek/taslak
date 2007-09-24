package org.xmdl.taslak.service;

import org.xmdl.taslak.model.Product;
import org.xmdl.taslak.model.search.ProductSearch;

import java.util.Collection;

public interface ProductManager extends GenericManager<Product,Long>{
    Collection<Product> search(ProductSearch productSearch);
}
