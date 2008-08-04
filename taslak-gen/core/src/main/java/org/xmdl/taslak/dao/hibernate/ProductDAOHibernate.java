
package org.xmdl.taslak.dao.hibernate;


import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.Session;

import org.hibernate.criterion.Restrictions;

import org.xmdl.ida.lib.dao.hibernate.GenericDaoHibernate;

import org.xmdl.mesken.model.User;
import org.xmdl.taslak.dao.*;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*; 











/**
 *
 * Product DAO Hibernate implementation
 *  
 * $Id$
 *
 * @generated
 */ 
public class ProductDAOHibernate extends GenericDaoHibernate<Product, Long> implements ProductDAO {

    /**
     * Public default constructor
     * @generated
     */ 
    public ProductDAOHibernate() {
        super(Product.class);
    }

    /**
     * @generated
     */ 
    public Collection<Product> search(ProductSearch productSearch) {
        if (log.isDebugEnabled()) {
            log.debug("search(ProductSearch productSearch) <-");
            log.debug("productSearch: " + productSearch);
        }
        
        Collection<Product> list = null;
        if (productSearch == null) {
            list = new ArrayList<Product>();
        } else {
            String name = productSearch.getName();
            Double minPrice = productSearch.getPriceMin();
            Double maxPrice = productSearch.getPriceMax();
            OrderElement orderElements = productSearch.getOrderElements();
            Supplier suppliers = productSearch.getSuppliers();

            if (log.isDebugEnabled()) {
                log.debug("search(ProductSearch <-");
                log.debug("name       : " + name);
                log.debug("minPrice       : " + minPrice);
                log.debug("maxPrice       : " + maxPrice);
                log.debug("orderElements       : " + orderElements);
                log.debug("suppliers       : " + suppliers);
            }

                Session session = getSession();
                Criteria criteria = session.createCriteria(Product.class);
            if (name != null)
                criteria.add(Restrictions.eq("name", name));
            if (minPrice != null)
                criteria.add(Restrictions.ge("price", minPrice));
            if (maxPrice != null)
                criteria.add(Restrictions.le("price", maxPrice));
            if (orderElements != null){
                Criteria subCriteria = criteria.createCriteria("orderElements");
                subCriteria.add(Restrictions.idEq(orderElements.getId()));
            }
            if (suppliers != null){
                Criteria subCriteria = criteria.createCriteria("suppliers");
                subCriteria.add(Restrictions.idEq(suppliers.getId()));
            }
            list = criteria.list();

        }


        if (log.isDebugEnabled())
            log.debug("search(ProductSearch productSearch) ->");
        return list;
    }

}
