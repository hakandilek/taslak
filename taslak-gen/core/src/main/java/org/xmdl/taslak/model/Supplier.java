
package org.xmdl.taslak.model;


import java.io.Serializable;

import java.text.MessageFormat;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import org.xmdl.ida.lib.model.BaseObject;
import org.xmdl.mesken.model.User; 










/**
 *
 * Supplier entity bean
 *  
 * $Id$
 *
 * @generated
 */ 
@Entity (name=("TBL_SUPPLIER"))
public class Supplier extends BaseObject implements Serializable, Cloneable {

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
    @ManyToMany(cascade = { CascadeType.PERSIST,CascadeType.MERGE },mappedBy = "suppliers", targetEntity = Product.class)
    private Set<Product> products;
    

    /** 
     * @generated
     */
    @Embedded
    private Contact contact = new Contact();


    /** 
     * @generated
     */
    public Supplier() {
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
    public Set<Product> getProducts() {
        return products;
    }

    /** 
     * @generated
     */
    public void setProducts(Set<Product> products) {
        this.products = products;
    }


    /** 
     * @generated
     */
    public Contact getContact() {
        return contact;
    }

    /** 
     * @generated
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /** 
     * @generated
     */
    public String toString() {
        return MessageFormat.format("Supplier [id={0}][name={1}]", id, name);
    }

    /** 
     * @generated
     */
    public boolean equals(Object o) {
        return o instanceof Supplier && ((Supplier) o).getId() == this.getId();
    }

    /** 
     * @generated
     */
    public int hashCode() {
        int result = 1;
        if (id != null) result = 31 * result + id.hashCode();
        if (name != null) result = 31 * result + name.hashCode();
        return result;
    }

    /** 
     * @generated
     */
     public Supplier createClone() {
     	try {
     		return (Supplier) clone();
        } catch (CloneNotSupportedException e) {
            Supplier copy = new Supplier();
            copy.name = this.name;
            return copy;
        }
     }


}
