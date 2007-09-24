package org.xmdl.taslak.model.search;

import org.xmdl.taslak.model.OrderElement;

public class OrderElementSearch {
    OrderElement fromOrderElement;
    OrderElement toOrderElement;

    public OrderElementSearch() {
    }

    public OrderElement getFromOrderElement() {
        return fromOrderElement;
    }

    public void setFromOrderElement(OrderElement fromOrderElement) {
        this.fromOrderElement = fromOrderElement;
    }

    public OrderElement getToOrderElement() {
        return toOrderElement;
    }

    public void setToOrderElement(OrderElement toOrderElement) {
        this.toOrderElement = toOrderElement;
    }
}
