package org.xmdl.taslak.dao.hibernate;

import org.xmdl.ida.lib.dao.hibernate.GenericDaoHibernate;
import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.model.Product;
import org.xmdl.taslak.model.ProductType;
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
    	if (log.isDebugEnabled()) {
    		log.debug("search(ProductSearch productSearch) <-");
        	log.debug("productSearch: " + productSearch);
    	}
    	
		Collection<Product> list = null;
		if (productSearch == null) {
			list = new ArrayList<Product>();
		} else {
			String name = productSearch.getName();
			Double fromPrice = productSearch.getFromPrice();
			Double toPrice = productSearch.getToPrice();
			ProductType productType = productSearch.getProductType();

			list = search(name, fromPrice, toPrice, productType);
		}

		if (log.isDebugEnabled())
			log.debug("search(ProductSearch productSearch) ->");
    	return list;
    }

    public Collection<Product> search(String name, Double fromPrice, Double toPrice, ProductType productType) {
    	if (log.isDebugEnabled()) {
    		log.debug("search(String name, Double fromPrice, Double toPrice) <-");
        	log.debug("name       : " + name);
        	log.debug("fromPrice  : " + fromPrice);
        	log.debug("toPrice    : " + toPrice);
            log.debug("productType: " + productType);
    	}
    	
        Criteria criteria = getSession().createCriteria(Product.class);

        if (name != null && !name.equals(""))
            criteria.add(Restrictions.like("name", "%" + name + "%"));
        if (fromPrice != null)
            criteria.add(Restrictions.ge("price", fromPrice));
        if (toPrice != null)
            criteria.add(Restrictions.le("price", toPrice));
        if (productType != null){
            criteria.add(Restrictions.eq("productType", productType));
        }

        List<Product> list = criteria.list();
        
        if (log.isDebugEnabled()) log.debug("search(String name, Double fromPrice, Double toPrice, ProductType productType) <-");
		return list;
    }
}
