
package org.xmdl.taslak.model.search;


import java.io.Serializable;

import java.text.MessageFormat;

import java.util.*;
import org.xmdl.taslak.model.*; 





/**
 *
 * OrderElement entity search bean
 *  
 * $Id$
 *
 * @generated
 */ 
public class OrderElementSearch implements Serializable {

    /** 
     * @generated
     */
    private Long quantityMin;

    /** 
     * @generated
     */
    private Long quantityMax;
    

    /** 
     * @generated
     */
    private Order order;
    

    /** 
     * @generated
     */
    private Product product;
    

    /** 
     * @generated
     */
    public OrderElementSearch() {
    }


    /** 
     * @generated
     */
    public Long getQuantityMin() {
        return quantityMin;
    }

    /** 
     * @generated
     */
    public void setQuantityMin(Long quantityMin) {
        this.quantityMin = quantityMin;
    }

    /** 
     * @generated
     */
    public Long getQuantityMax() {
        return quantityMax;
    }

    /** 
     * @generated
     */
    public void setQuantityMax(Long quantityMax) {
        this.quantityMax = quantityMax;
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
        return MessageFormat.format("OrderElementSearch "
            + "[quantityMin={0}]"
            + "[quantityMax={1}]"
            + "[order={2}]"
            + "[product={3}]"
            , quantityMin
            , quantityMax
            , order
            , product
        );
    }

    /** 
     * @generated
     */
    public int hashCode() {
        int result = 1;
            result = 31 * result + quantityMin.hashCode();
            result = 31 * result + quantityMax.hashCode();
            result = 31 * result + order.hashCode();
            result = 31 * result + product.hashCode();
        return result;
    }


}
