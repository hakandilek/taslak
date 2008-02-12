package org.xmdl.taslak.dao;

import org.xmdl.ida.lib.dao.GenericDao;
import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.model.search.OrderSearch;

import java.util.Collection;
import java.util.Date;

public interface OrderDao extends GenericDao<Order, Long> {
    Collection<Order> search(String name, Double fromPriceTotals, Double toPriceTotals, Date fromDate, Date toDate);
    Collection<Order> search(OrderSearch orderSearch);
}
