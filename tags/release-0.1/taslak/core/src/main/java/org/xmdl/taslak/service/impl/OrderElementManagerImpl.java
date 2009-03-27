package org.xmdl.taslak.service.impl;

import org.xmdl.ida.lib.service.impl.GenericManagerImpl;
import org.xmdl.taslak.model.OrderElement;
import org.xmdl.taslak.model.Order;
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

    public void copyOrderElementsFrom(Order fromOrder, Order toOrder) {
        OrderElementSearch oeSearch = new OrderElementSearch();
        oeSearch.setOrder(fromOrder);

        Collection<OrderElement> orderElements = orderElementDao.search(oeSearch);
        for (OrderElement orderElement : orderElements) {
            OrderElement o = copyFrom(orderElement);
            o.setOrder(toOrder);
            save(o);
        }
    }

    private OrderElement copyFrom(OrderElement orderElement){
        return orderElementDao.copyFrom(orderElement);
    }
}