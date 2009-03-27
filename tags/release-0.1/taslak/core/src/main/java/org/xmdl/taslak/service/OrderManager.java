package org.xmdl.taslak.service;

import org.xmdl.ida.lib.service.GenericManager;
import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.model.search.OrderSearch;

import java.util.Collection;

public interface OrderManager extends GenericManager<Order,Long>{
    Collection<Order> search(OrderSearch orderSearch);
}
