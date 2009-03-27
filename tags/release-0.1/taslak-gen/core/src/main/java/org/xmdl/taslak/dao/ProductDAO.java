 
package org.xmdl.taslak.dao;


import java.util.*;

import org.xmdl.ida.lib.dao.GenericDao;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*; 







/**
 *
 * Product DAO interface
 *  
 * $Id$
 *
 * @generated
 */ 
public interface ProductDAO extends GenericDao<Product, Long> {

    /**
     * @generated
     */ 
    Collection<Product> search(ProductSearch searchBean);

}
