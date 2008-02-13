package org.xmdl.taslak.dao.hibernate;

import org.xmdl.ida.lib.dao.hibernate.GenericDaoHibernate;
import org.xmdl.taslak.dao.OrderDao;
import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.model.search.OrderSearch;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;

public class OrderDaoHibernate extends GenericDaoHibernate<Order, Long> implements OrderDao {

    public OrderDaoHibernate() {
        super(Order.class);
    }

    @SuppressWarnings("unchecked")
	public Collection<Order> search(OrderSearch orderSearch) {
        if (orderSearch == null)
            return new ArrayList<Order>();

        String name = orderSearch.getName();
        Double fromPriceTotals = orderSearch.getFromPriceTotals();
        Double toPriceTotals = orderSearch.getToPriceTotals();
        Date fromCreateDate = orderSearch.getFromCreateDate();
        Date toCreateDate = orderSearch.getToCreateDate();
        
        Criteria criteria = getSession().createCriteria(Order.class);

        if (name != null && !name.equals(""))
            criteria.add(Restrictions.like("name", "%" + name + "%"));
        if (fromPriceTotals != null)
            criteria.add(Restrictions.ge("priceTotals", fromPriceTotals));
        if (toPriceTotals != null)
            criteria.add(Restrictions.le("priceTotals", toPriceTotals));
        if (fromCreateDate != null)
            criteria.add(Restrictions.ge("createDate", fromCreateDate));
        if (toCreateDate != null)
            criteria.add(Restrictions.le("createDate", toCreateDate));

        return criteria.list();
    }
}