
package org.xmdl.taslak.dao.hibernate;


import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.Session;

import org.hibernate.criterion.Restrictions;

import org.xmdl.ida.lib.dao.hibernate.GenericDaoHibernate;

import org.xmdl.taslak.dao.*;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*; 











/**
 *
 * Order DAO Hibernate implementation
 *  
 * $Id$
 *
 * @generated
 */ 
public class OrderDAOHibernate extends GenericDaoHibernate<Order, Long> implements OrderDAO {

    /**
     * Public default constructor
     * @generated
     */ 
    public OrderDAOHibernate() {
        super(Order.class);
    }

    /**
     * @generated
     */ 
    public Collection<Order> search(OrderSearch orderSearch) {
        if (log.isDebugEnabled()) {
            log.debug("search(OrderSearch orderSearch) <-");
            log.debug("orderSearch: " + orderSearch);
        }
        
        Collection<Order> list = null;
        if (orderSearch == null) {
            list = new ArrayList<Order>();
        } else {
            String name = orderSearch.getName();
            Double minPriceTotals = orderSearch.getPriceTotalsMin();
            Double maxPriceTotals = orderSearch.getPriceTotalsMax();
            Date minCreateDate = orderSearch.getCreateDateMin();
            Date maxCreateDate = orderSearch.getCreateDateMax();
            OrderElement orderElements = orderSearch.getOrderElements();

            if (log.isDebugEnabled()) {
                log.debug("search(OrderSearch <-");
                log.debug("name       : " + name);
                log.debug("minPriceTotals       : " + minPriceTotals);
                log.debug("maxPriceTotals       : " + maxPriceTotals);
                log.debug("minCreateDate       : " + minCreateDate);
                log.debug("maxCreateDate       : " + maxCreateDate);
                log.debug("orderElements       : " + orderElements);
            }

                Session session = getSession();
                Criteria criteria = session.createCriteria(Order.class);
            if (name != null)
                criteria.add(Restrictions.eq("name", name));
            if (minPriceTotals != null)
                criteria.add(Restrictions.ge("priceTotals", minPriceTotals));
            if (maxPriceTotals != null)
                criteria.add(Restrictions.le("priceTotals", maxPriceTotals));
            if (minCreateDate != null)
                criteria.add(Restrictions.ge("createDate", minCreateDate));
            if (maxCreateDate != null)
                criteria.add(Restrictions.le("createDate", maxCreateDate));
            if (orderElements != null){
                Criteria subCriteria = criteria.createCriteria("orderElements");
                subCriteria.add(Restrictions.idEq(orderElements.getId()));
            }
            list = criteria.list();

        }


        if (log.isDebugEnabled())
            log.debug("search(OrderSearch orderSearch) ->");
        return list;
    }

}
