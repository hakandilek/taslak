
package org.xmdl.taslak.model;


import java.io.Serializable;

import java.text.MessageFormat;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import org.xmdl.ida.lib.model.BaseObject; 










/**
 *
 * OrderElement entity bean
 *  
 * $Id$
 *
 * @generated
 */ 
@Entity (name=("TBL_ORDERELEMENT"))
public class OrderElement extends BaseObject implements Serializable {

    /** 
     * Unique identifier
     * 
     * @generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ("ID"))
    private Long id;

    /** 
     * @generated
     */
    @Column(name = ("F_QUANTITY"), length = 15)
    private Long quantity;
    
    /** 
     * @generated
     */
    @ManyToOne()
	@JoinColumn(name = ("F_ORDER_ID"))
    private Order order;
    
    /** 
     * @generated
     */
    @ManyToOne()
	@JoinColumn(name = ("F_PRODUCT_ID"))
    private Product product;
    

    /** 
     * @generated
     */
    public OrderElement() {
    }

    /** 
     * @generated
     */
    public Long getId() {
        return id;
    }

    /** 
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /** 
     * @generated
     */
    public Long getQuantity() {
        return quantity;
    }

    /** 
     * @generated
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    /** 
     * @generated
     */
    public Order getOrder() {
        return order;
    }

    /** 
     * @generated
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /** 
     * @generated
     */
    public Product getProduct() {
        return product;
    }

    /** 
     * @generated
     */
    public void setProduct(Product product) {
        this.product = product;
    }


    /** 
     * @generated
     */
    public String toString() {
        return MessageFormat.format("OrderElement [id={0}][quantity={1}]", id, quantity);
    }

    /** 
     * @generated
     */
    public boolean equals(Object o) {
        return o instanceof OrderElement && ((OrderElement) o).getId() == this.getId();
    }

    /** 
     * @generated
     */
    public int hashCode() {
        int result;
        result = id.hashCode();
        result = 31 * result + quantity.hashCode();
        result = 31 * result + order.hashCode();
        result = 31 * result + product.hashCode();
        return result;
    }


}
