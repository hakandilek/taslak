 
package org.xmdl.taslak.dao;


import java.util.*;

import org.xmdl.ida.lib.dao.GenericDao;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*; 







/**
 *
 * Order DAO interface
 *  
 * $Id$
 *
 * @generated
 */ 
public interface OrderDAO extends GenericDao<Order, Long> {

    /**
     * @generated
     */ 
    Collection<Order> search(OrderSearch searchBean);

}
