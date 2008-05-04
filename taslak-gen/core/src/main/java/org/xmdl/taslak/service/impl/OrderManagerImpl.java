
package org.xmdl.taslak.service.impl;


import java.util.Collection;

import org.xmdl.ida.lib.service.impl.GenericManagerImpl;

import org.xmdl.taslak.dao.*;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*;

import org.xmdl.taslak.service.*; 









/**
 *
 * Order Service implementation
 *  
 * $Id$
 *
 * @generated
 */ 
public class OrderManagerImpl extends GenericManagerImpl<Order, Long> implements OrderManager {

    /**
     * @generated
     */ 
    OrderDAO orderDAO;

    /**
     * @generated
     */ 
    public OrderManagerImpl(OrderDAO orderDAO) {
        super(orderDAO);
        this.orderDAO = orderDAO;
    }

    /**
     * @generated
     */ 
    public Collection<Order> search(OrderSearch orderSearch) {
        return orderDAO.search(orderSearch);
    }

}
