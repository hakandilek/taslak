
package org.xmdl.taslak.service.impl;


import java.util.Collection;

import org.xmdl.ida.lib.service.impl.GenericManagerImpl;

import org.xmdl.taslak.dao.*;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*;

import org.xmdl.taslak.service.*; 









/**
 *
 * Product Service implementation
 *  
 * $Id$
 *
 * @generated
 */ 
public class ProductManagerImpl extends GenericManagerImpl<Product, Long> implements ProductManager {

    /**
     * @generated
     */ 
    ProductDAO productDAO;

    /**
     * @generated
     */ 
    public ProductManagerImpl(ProductDAO productDAO) {
        super(productDAO);
        this.productDAO = productDAO;
    }

    /**
     * @generated
     */ 
    public Collection<Product> search(ProductSearch productSearch) {
        return productDAO.search(productSearch);
    }

}
