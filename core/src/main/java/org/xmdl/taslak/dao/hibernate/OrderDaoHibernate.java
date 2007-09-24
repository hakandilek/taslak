package org.xmdl.taslak.dao.hibernate;

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

    public Collection<Order> search(String name, Double fromPriceTotals, Double toPriceTotals, Date fromCreateDate, Date toCreateDate) {

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

    public Collection<Order> search(OrderSearch orderSearch) {
        if (orderSearch == null)
            return new ArrayList<Order>();

        return search(orderSearch.getName(), orderSearch.getFromPriceTotals(), orderSearch.getToPriceTotals(), orderSearch.getFromCreateDate(), orderSearch.getToCreateDate());
    }
}