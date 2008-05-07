
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
 * Product entity bean
 *  
 * $Id$
 *
 * @generated
 */ 
@Entity (name=("TBL_PRODUCT"))
public class Product extends BaseObject implements Serializable, Cloneable {

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
    @Column(name = ("F_NAME"), length = 15)
    private String name;
    
    /** 
     * @generated
     */
    @Column(name = ("F_PRICE"), length = 15)
    private Double price;
    
    /** 
     * @generated
     */
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = ("product"))
    private Set<OrderElement> orderElements;
    
    /** 
     * @generated
     */
    @ManyToMany(targetEntity = Supplier.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "TBL_PRODUCT_SUPPLIER",    joinColumns = { @JoinColumn(name = "F_PRODUCTS_ID") },    inverseJoinColumns = { @JoinColumn(name = "F_SUPPLIERS_ID") })
    private Set<Supplier> suppliers;
    

    /** 
     * @generated
     */
    public Product() {
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
    public Double getPrice() {
        return price;
    }

    /** 
     * @generated
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /** 
     * @generated
     */
    public Set<OrderElement> getOrderElements() {
        return orderElements;
    }

    /** 
     * @generated
     */
    public void setOrderElements(Set<OrderElement> orderElements) {
        this.orderElements = orderElements;
    }

    /** 
     * @generated
     */
    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    /** 
     * @generated
     */
    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }


    /** 
     * @generated
     */
    public String toString() {
        return MessageFormat.format("Product [id={0}][name={1}][price={2}]", id, name, price);
    }

    /** 
     * @generated
     */
    public boolean equals(Object o) {
        return o instanceof Product && ((Product) o).getId() == this.getId();
    }

    /** 
     * @generated
     */
    public int hashCode() {
        int result;
        result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    /** 
     * @generated
     */
     public Product createClone() {
     	try {
     		return (Product) clone();
        } catch (CloneNotSupportedException e) {
            Product copy = new Product();
            copy.name = this.name;
            copy.price = this.price;
            return copy;
        }
     }


}
