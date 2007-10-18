package org.xmdl.taslak.dao.hibernate;

import org.xmdl.ida.lib.dao.hibernate.GenericDaoHibernate;
import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.model.Product;
import org.xmdl.taslak.model.search.ProductSearch;
import org.xmdl.taslak.dao.ProductDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoHibernate extends GenericDaoHibernate<Product, Long> implements ProductDao {

    public ProductDaoHibernate() {
        super(Product.class);
    }

    public Collection<Order> search(String name, Double fromPriceTotals, Double toPriceTotals, Date fromCreateDate, Date toCreateDate) {

        Criteria criteria = getSession().createCriteria(Order.class);

        if (name != null && !name.equals(""))
            criteria.add(Restrictions.like("name", "%" + name + "%"));
        if (fromPriceTotals != null)
            criteria.add(Restrictions.ge("priceTotals", fromPriceTotals));
        if (toPriceTotals != null)
            criteria.add(Restrictions.le("priceTotals", toPriceTotals));
        if (fromCreateDate != null)
            criteria.add(Restrictions.ge("date", fromCreateDate));
        if (toCreateDate != null)
            criteria.add(Restrictions.le("date", toCreateDate));

        return criteria.list();
    }

    public Collection<Product> search(ProductSearch productSearch) {
        if (productSearch == null)
            return new ArrayList<Product>();

        return search(productSearch.getName(), productSearch.getFromPrice(), productSearch.getToPrice());
    }

    public Collection<Product> search(String name, Double fromPrice, Double toPrice) {
    	if (log.isDebugEnabled()) log.debug("search(String name, Double fromPrice, Double toPrice) <-");
    	
    	if (log.isDebugEnabled()) log.debug("name      : " + name);
    	if (log.isDebugEnabled()) log.debug("fromPrice : " + fromPrice);
    	if (log.isDebugEnabled()) log.debug("toPrice   : " + toPrice);
    	
        Criteria criteria = getSession().createCriteria(Product.class);

        if (name != null && !name.equals(""))
            criteria.add(Restrictions.like("name", "%" + name + "%"));
        if (fromPrice != null)
            criteria.add(Restrictions.ge("price", fromPrice));
        if (toPrice != null)
            criteria.add(Restrictions.le("price", toPrice));

        List list = criteria.list();
        
        if (log.isDebugEnabled()) log.debug("search(String name, Double fromPrice, Double toPrice) <-");
		return list;
    }
}
