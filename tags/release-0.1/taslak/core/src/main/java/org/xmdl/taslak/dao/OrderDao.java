package org.xmdl.taslak.dao;

import java.util.Collection;

import org.xmdl.ida.lib.dao.GenericDao;
import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.model.search.OrderSearch;

public interface OrderDao extends GenericDao<Order, Long> {
    Collection<Order> search(OrderSearch orderSearch);
}
