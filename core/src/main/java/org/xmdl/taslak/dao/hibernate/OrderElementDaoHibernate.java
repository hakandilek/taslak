package org.xmdl.taslak.dao.hibernate;

import org.xmdl.ida.lib.dao.hibernate.GenericDaoHibernate;
import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.model.OrderElement;
import org.xmdl.taslak.model.Product;
import org.xmdl.taslak.model.search.OrderElementSearch;
import org.xmdl.taslak.dao.OrderElementDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.ArrayList;

public class OrderElementDaoHibernate extends GenericDaoHibernate<OrderElement, Long> implements OrderElementDao {

    public OrderElementDaoHibernate() {
        super(OrderElement.class);
    }

    public Collection<OrderElement> search(OrderElementSearch orderElementSearch) {
        if(orderElementSearch == null){
            return new ArrayList<OrderElement>();
        }

        return search(orderElementSearch.getFromQuantity(), orderElementSearch.getToQuantity(), orderElementSearch.getOrder(), orderElementSearch.getProduct());
    }

    public Collection<OrderElement> search(Long fromQuantity, Long toQuantity, Order order, Product product) {

        Criteria criteria = getSession().createCriteria(OrderElement.class);

        if (fromQuantity != null)
            criteria.add(Restrictions.ge("quantity", fromQuantity));
        if (toQuantity != null)
            criteria.add(Restrictions.le("quantity", toQuantity));
        if (order != null && order.getId() != Integer.MIN_VALUE)
            criteria.add(Restrictions.eq("order.id", order.getId()));
        if (product != null && product.getId() != Integer.MIN_VALUE)
            criteria.add(Restrictions.eq("product.id", product.getId()));

        return criteria.list();
    }
}
