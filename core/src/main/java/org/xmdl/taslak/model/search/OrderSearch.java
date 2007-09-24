package org.xmdl.taslak.model.search;

import org.xmdl.taslak.model.Order;

public class OrderSearch {
    Order fromOrder;
    Order toOrder;

    public OrderSearch() {
    }

    public Order getFromOrder() {
        return fromOrder;
    }

    public void setFromOrder(Order fromOrder) {
        this.fromOrder = fromOrder;
    }

    public Order getToOrder() {
        return toOrder;
    }

    public void setToOrder(Order toOrder) {
        this.toOrder = toOrder;
    }
}
