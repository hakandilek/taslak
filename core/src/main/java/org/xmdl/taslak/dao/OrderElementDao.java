package org.xmdl.taslak.dao;

import org.xmdl.ida.lib.dao.GenericDao;
import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.model.OrderElement;
import org.xmdl.taslak.model.Product;
import org.xmdl.taslak.model.search.OrderElementSearch;

import java.util.Collection;

public interface OrderElementDao extends GenericDao<OrderElement, Long> {
    Collection<OrderElement> search(Long fromQuantity, Long toQuantity, Order order, Product product);
    Collection<OrderElement> search(OrderElementSearch orderElementSearch);
}
