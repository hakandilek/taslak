package org.xmdl.taslak.service;

import org.xmdl.taslak.model.OrderElement;
import org.xmdl.taslak.model.search.OrderElementSearch;

import java.util.Collection;

public interface OrderElementManager extends GenericManager<OrderElement,Long>{
    Collection<OrderElement> search(OrderElementSearch orderElementSearch);
}
