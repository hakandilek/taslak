package org.xmdl.taslak.dao;

import java.util.Collection;

import org.xmdl.ida.lib.dao.GenericDao;
import org.xmdl.taslak.model.OrderElement;
import org.xmdl.taslak.model.search.OrderElementSearch;

public interface OrderElementDao extends GenericDao<OrderElement, Long> {
    Collection<OrderElement> search(OrderElementSearch orderElementSearch);

    OrderElement copyFrom(OrderElement orderElement);
}
