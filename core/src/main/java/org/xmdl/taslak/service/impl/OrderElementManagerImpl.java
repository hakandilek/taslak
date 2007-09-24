package org.xmdl.taslak.service.impl;

import org.xmdl.taslak.model.OrderElement;
import org.xmdl.taslak.model.search.OrderElementSearch;
import org.xmdl.taslak.service.OrderElementManager;
import org.xmdl.taslak.dao.OrderElementDao;

import java.util.Collection;

public class OrderElementManagerImpl extends GenericManagerImpl<OrderElement, Long> implements OrderElementManager {

    OrderElementDao orderElementDao;

    public OrderElementManagerImpl(OrderElementDao orderElementDao) {
        super(orderElementDao);
        this.orderElementDao = orderElementDao;
    }

    public Collection<OrderElement> search(OrderElementSearch orderElementSearch) {
        return orderElementDao.search(orderElementSearch);
    }
}
