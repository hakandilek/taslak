
package org.xmdl.taslak.model.search;


import java.io.Serializable;

import java.text.MessageFormat;

import java.util.*;
import org.xmdl.taslak.model.*; 





/**
 *
 * Product entity search bean
 *  
 * $Id$
 *
 * @generated
 */ 
public class ProductSearch implements Serializable {

    /** 
     * @generated
     */
    private String name;
    

    /** 
     * @generated
     */
    private Double priceMin;

    /** 
     * @generated
     */
    private Double priceMax;
    

    /** 
     * @generated
     */
    private OrderElement orderElements;
    

    /** 
     * @generated
     */
    private Supplier suppliers;
    

    /** 
     * @generated
     */
    public ProductSearch() {
    }


    /** 
     * @generated
     */
    public String getName() {
        return name;
    }

    /** 
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }


    /** 
     * @generated
     */
    public Double getPriceMin() {
        return priceMin;
    }

    /** 
     * @generated
     */
    public void setPriceMin(Double priceMin) {
        this.priceMin = priceMin;
    }

    /** 
     * @generated
     */
    public Double getPriceMax() {
        return priceMax;
    }

    /** 
     * @generated
     */
    public void setPriceMax(Double priceMax) {
        this.priceMax = priceMax;
    }


    /** 
     * @generated
     */
    public OrderElement getOrderElements() {
        return orderElements;
    }

    /** 
     * @generated
     */
    public void setOrderElements(OrderElement orderElements) {
        this.orderElements = orderElements;
    }


    /** 
     * @generated
     */
    public Supplier getSuppliers() {
        return suppliers;
    }

    /** 
     * @generated
     */
    public void setSuppliers(Supplier suppliers) {
        this.suppliers = suppliers;
    }


    /** 
     * @generated
     */
    public String toString() {
        return MessageFormat.format("ProductSearch "
            + "[name={0}]"
            + "[priceMin={1}]"
            + "[priceMax={2}]"
            + "[orderElements={3}]"
            + "[suppliers={4}]"
            , name
            , priceMin
            , priceMax
            , orderElements
            , suppliers
        );
    }

    /** 
     * @generated
     */
    public int hashCode() {
        int result = 1;
            result = 31 * result + name.hashCode();
            result = 31 * result + priceMin.hashCode();
            result = 31 * result + priceMax.hashCode();
            result = 31 * result + orderElements.hashCode();
            result = 31 * result + suppliers.hashCode();
        return result;
    }


}
