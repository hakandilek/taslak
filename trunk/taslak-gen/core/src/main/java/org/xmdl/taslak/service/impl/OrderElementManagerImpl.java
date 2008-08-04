
package org.xmdl.taslak.service.impl;


import java.util.Collection;

import org.xmdl.ida.lib.service.impl.GenericManagerImpl;

import org.xmdl.taslak.dao.*;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*;

import org.xmdl.taslak.service.*; 









/**
 *
 * OrderElement Service implementation
 *  
 * $Id$
 *
 * @generated
 */ 
public class OrderElementManagerImpl extends GenericManagerImpl<OrderElement, Long> implements OrderElementManager {

    /**
     * @generated
     */ 
    private final OrderElementDAO orderElementDAO;

    /**
     * @generated
     */ 
    public OrderElementManagerImpl(OrderElementDAO orderElementDAO) {
        super(orderElementDAO);
        this.orderElementDAO = orderElementDAO;
    }

    /**
     * @generated
     */ 
    public Collection<OrderElement> search(OrderElementSearch orderElementSearch) {
        return orderElementDAO.search(orderElementSearch);
    }

    /**
     * @generated
     */ 
    public void copyFrom(Order source, Order destination) {
        OrderElementSearch search = new OrderElementSearch();
        search.setOrder(source);

        Collection<OrderElement> children = orderElementDAO.search(search);
        for (OrderElement child : children) {
            OrderElement copy = child.createClone();
            copy.setOrder(destination);
            save(copy);
        }
    }

}
