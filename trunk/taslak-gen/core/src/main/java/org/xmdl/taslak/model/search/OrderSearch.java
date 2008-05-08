
package org.xmdl.taslak.model.search;


import java.io.Serializable;

import java.text.MessageFormat;

import java.util.*;
import org.xmdl.taslak.model.*; 





/**
 *
 * Order entity search bean
 *  
 * $Id$
 *
 * @generated
 */ 
public class OrderSearch implements Serializable {

    /** 
     * @generated
     */
    private String name;
    

    /** 
     * @generated
     */
    private Double priceTotalsMin;

    /** 
     * @generated
     */
    private Double priceTotalsMax;
    

    /** 
     * @generated
     */
    private Date createDateMin;

    /** 
     * @generated
     */
    private Date createDateMax;
    

    /** 
     * @generated
     */
    private OrderElement orderElements;
    

    /** 
     * @generated
     */
    public OrderSearch() {
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
    public Double getPriceTotalsMin() {
        return priceTotalsMin;
    }

    /** 
     * @generated
     */
    public void setPriceTotalsMin(Double priceTotalsMin) {
        this.priceTotalsMin = priceTotalsMin;
    }

    /** 
     * @generated
     */
    public Double getPriceTotalsMax() {
        return priceTotalsMax;
    }

    /** 
     * @generated
     */
    public void setPriceTotalsMax(Double priceTotalsMax) {
        this.priceTotalsMax = priceTotalsMax;
    }


    /** 
     * @generated
     */
    public Date getCreateDateMin() {
        return createDateMin;
    }

    /** 
     * @generated
     */
    public void setCreateDateMin(Date createDateMin) {
        this.createDateMin = createDateMin;
    }

    /** 
     * @generated
     */
    public Date getCreateDateMax() {
        return createDateMax;
    }

    /** 
     * @generated
     */
    public void setCreateDateMax(Date createDateMax) {
        this.createDateMax = createDateMax;
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
    @Override
    public String toString() {
        return MessageFormat.format("OrderSearch "
            + "[name={0}]"
            + "[priceTotalsMin={1}]"
            + "[priceTotalsMax={2}]"
            + "[createDateMin={3}]"
            + "[createDateMax={4}]"
            + "[orderElements={5}]"
            , name
            , priceTotalsMin
            , priceTotalsMax
            , createDateMin
            , createDateMax
            , orderElements
        );
    }

    /** 
     * @generated
     */
    @Override
    public int hashCode() {
        int result = 1;
        if (name != null) result = 31 * result + name.hashCode();
        if (priceTotalsMin != null) result = 31 * result + priceTotalsMin.hashCode();
        if (priceTotalsMax != null) result = 31 * result + priceTotalsMax.hashCode();
        if (createDateMin != null) result = 31 * result + createDateMin.hashCode();
        if (createDateMax != null) result = 31 * result + createDateMax.hashCode();
        if (orderElements != null) result = 31 * result + orderElements.hashCode();
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }


}
