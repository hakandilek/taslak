package org.xmdl.taslak.model;

import javax.persistence.*;

import org.xmdl.ida.lib.model.BaseObject;

import java.io.Serializable;
import java.text.MessageFormat;

@Entity (name=("t_orderElement"))
public class OrderElement extends BaseObject implements Serializable  {

    private Long id;
    private Long quantity;
    private Order order;
    private Product product;

    public OrderElement() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name=("id"))
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = ("quantity"), nullable = false)
    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @ManyToOne()
    @JoinColumn(name = ("orderId"), nullable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne()
    @JoinColumn(name = ("productId"), nullable = true)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String toString() {
        return MessageFormat.format("OrderElement [id={0}][quantity={1}][order={2}][product={3}]",id,quantity,order,product);
    }

    public boolean equals(Object o) {
        return o instanceof OrderElement && ((OrderElement) o).getId() == this.getId();
    }

    public int hashCode() {
        int result ;
        result = id.hashCode();
        return result;
    }
}
