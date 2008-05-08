
package org.xmdl.taslak.model.search;


import java.io.Serializable;

import java.text.MessageFormat;

import java.util.*;
import org.xmdl.taslak.model.*; 





/**
 *
 * Supplier entity search bean
 *  
 * $Id$
 *
 * @generated
 */ 
public class SupplierSearch implements Serializable {

    /** 
     * @generated
     */
    private String name;
    

    /** 
     * @generated
     */
    private Product products;
    

    /** 
     * @generated
     */
    public SupplierSearch() {
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
    public Product getProducts() {
        return products;
    }

    /** 
     * @generated
     */
    public void setProducts(Product products) {
        this.products = products;
    }


    /** 
     * @generated
     */
    @Override
    public String toString() {
        return MessageFormat.format("SupplierSearch "
            + "[name={0}]"
            + "[products={1}]"
            , name
            , products
        );
    }

    /** 
     * @generated
     */
    @Override
    public int hashCode() {
        int result = 1;
        if (name != null) result = 31 * result + name.hashCode();
        if (products != null) result = 31 * result + products.hashCode();
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }


}
