
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
 * Contact entity bean
 *  
 * $Id$
 *
 * @generated
 */ 

@Embeddable
public class Contact extends BaseObject implements Serializable, Cloneable {

    /** 
     * @generated
     */
    @Column(name = ("F_FULLNAME"), length = 15)
    private String fullname;

    /** 
     * @generated
     */
    @Column(name = ("F_PHONE"), length = 15)
    private String phone;
    

    /** 
     * @generated
     */
    public Contact() {
    }

    /** 
     * @generated
     */
    public String getFullname() {
        return fullname;
    }

    /** 
     * @generated
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /** 
     * @generated
     */
    public String getPhone() {
        return phone;
    }

    /** 
     * @generated
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }


    /** 
     * @generated
     */
    public String toString() {
        return MessageFormat.format("Contact [fullname={1}][phone={2}]", fullname, phone);
    }

    /** 
     * @generated
     */
    public boolean equals(Object o) {
        return o instanceof Contact ;
    }

    /** 
     * @generated
     */
    public int hashCode() {
        int result = 1;
        
        if (fullname != null) result = 31 * result + fullname.hashCode();
        if (phone != null) result = 31 * result + phone.hashCode();
        return result;
    }

    /** 
     * @generated
     */
     public Contact createClone() {
     	try {
     		return (Contact) clone();
        } catch (CloneNotSupportedException e) {
            Contact copy = new Contact();
            copy.fullname = this.fullname;
            copy.phone = this.phone;
            return copy;
        }
     }

}
