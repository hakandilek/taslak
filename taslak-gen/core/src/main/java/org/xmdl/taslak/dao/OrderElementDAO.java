 
package org.xmdl.taslak.dao;


import java.util.*;

import org.xmdl.ida.lib.dao.GenericDao;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*; 







/**
 *
 * OrderElement DAO interface
 *  
 * $Id$
 *
 * @generated
 */ 
public interface OrderElementDAO extends GenericDao<OrderElement, Long> {

    /**
     * @generated
     */ 
    Collection<OrderElement> search(OrderElementSearch searchBean);

}
