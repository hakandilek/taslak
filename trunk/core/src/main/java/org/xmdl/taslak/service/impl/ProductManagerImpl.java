package org.xmdl.taslak.service.impl;

import org.xmdl.taslak.model.Product;
import org.xmdl.taslak.model.search.ProductSearch;
import org.xmdl.taslak.service.ProductManager;
import org.xmdl.taslak.dao.ProductDao;

import java.util.Collection;

public class ProductManagerImpl extends GenericManagerImpl<Product, Long> implements ProductManager {

    ProductDao productDao;

    public ProductManagerImpl(ProductDao productDao) {
        super(productDao);
        this.productDao = productDao;
    }

    public Collection<Product> search(ProductSearch productSearch) {
        return productDao.search(productSearch);
    }
}
