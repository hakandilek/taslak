package org.xmdl.taslak.service.impl;

import org.xmdl.taslak.service.OrderManager;
import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.model.search.OrderSearch;
import org.xmdl.taslak.dao.OrderDao;

import java.util.Collection;

public class OrderManagerImpl extends GenericManagerImpl<Order, Long> implements OrderManager {

    OrderDao orderDao;

    public OrderManagerImpl(OrderDao orderDao) {
        super(orderDao);
        this.orderDao = orderDao;
    }

    public Collection<Order> search(OrderSearch orderSearch) {
        return orderDao.search(orderSearch);
    }
}
