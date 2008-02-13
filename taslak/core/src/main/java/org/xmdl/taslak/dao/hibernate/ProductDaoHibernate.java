package org.xmdl.taslak.dao.hibernate;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.xmdl.ida.lib.dao.hibernate.GenericDaoHibernate;
import org.xmdl.taslak.dao.ProductDao;
import org.xmdl.taslak.model.Product;
import org.xmdl.taslak.model.ProductType;
import org.xmdl.taslak.model.Supplier;
import org.xmdl.taslak.model.search.ProductSearch;

public class ProductDaoHibernate extends GenericDaoHibernate<Product, Long> implements ProductDao {

    public ProductDaoHibernate() {
        super(Product.class);
    }

    @SuppressWarnings("unchecked")
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
			Supplier supplier = productSearch.getSupplier();

	    	if (log.isDebugEnabled()) {
	        	log.debug("name       : " + name);
	        	log.debug("fromPrice  : " + fromPrice);
	        	log.debug("toPrice    : " + toPrice);
	            log.debug("productType: " + productType);
	            log.debug("supplier   : " + supplier);
	    	}

	    	Session session = getSession();
			Criteria criteria = session.createCriteria(Product.class);

	        if (name != null && !name.equals(""))
	            criteria.add(Restrictions.like("name", "%" + name + "%"));
	        if (fromPrice != null)
	            criteria.add(Restrictions.ge("price", fromPrice));
	        if (toPrice != null)
	            criteria.add(Restrictions.le("price", toPrice));
	        if (productType != null){
	            criteria.add(Restrictions.eq("productType", productType));
	        }
	        if (supplier != null){
	            Criteria subCriteria = criteria.createCriteria("suppliers");
				subCriteria.add(Restrictions.idEq(supplier.getId()));
	        }

	        list = criteria.list();
		}

		if (log.isDebugEnabled())
			log.debug("search(ProductSearch productSearch) ->");
    	return list;
    }

}
