
package org.xmdl.taslak.service;


import java.util.Collection;

import org.xmdl.ida.lib.service.GenericManager;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*; 







/**
 *
 * Product Service interface
 *  
 * $Id$
 *
 * @generated
 */ 
public interface ProductManager extends GenericManager<Product, Long>{

    /**
     * @generated
     */ 
    Collection<Product> search(ProductSearch productSearch);

}
