package org.xmdl.taslak.model.search;

import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.model.Product;

public class OrderElementSearch {

    private Long fromQuantity;
    private Long toQuantity;
    private Order order;
    private Product product;

    public OrderElementSearch() {
    }

    public Long getFromQuantity() {
        return fromQuantity;
    }

    public void setFromQuantity(Long fromQuantity) {
        this.fromQuantity = fromQuantity;
    }

    public Long getToQuantity() {
        return toQuantity;
    }

    public void setToQuantity(Long toQuantity) {
        this.toQuantity = toQuantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
