
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
 * Profile entity bean
 *  
 * $Id$
 *
 * @generated
 */ 
@Entity (name=("TBL_PROFILE"))

public class Profile extends BaseObject implements Serializable, Cloneable {

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
    @Column(name = ("F_PRIVATEPHONE"), length = 15)
    private String privatePhone;
    
    /** 
     * @generated
     */
    @OneToOne(cascade = CascadeType.ALL)
@PrimaryKeyJoinColumn
    private User user;
    

    /** 
     * @generated
     */
    public Profile() {
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
    public String getPrivatePhone() {
        return privatePhone;
    }

    /** 
     * @generated
     */
    public void setPrivatePhone(String privatePhone) {
        this.privatePhone = privatePhone;
    }

    /** 
     * @generated
     */
    public User getUser() {
        return user;
    }

    /** 
     * @generated
     */
    public void setUser(User user) {
        this.user = user;
    }


    /** 
     * @generated
     */
    public String toString() {
        return MessageFormat.format("Profile [id={0}][privatePhone={1}]", id, privatePhone);
    }

    /** 
     * @generated
     */
    public boolean equals(Object o) {
        return o instanceof Profile && ((Profile) o).getId() == this.getId();
    }

    /** 
     * @generated
     */
    public int hashCode() {
        int result = 1;
        if (id != null) result = 31 * result + id.hashCode();
        if (privatePhone != null) result = 31 * result + privatePhone.hashCode();
        return result;
    }

    /** 
     * @generated
     */
     public Profile createClone() {
     	try {
     		return (Profile) clone();
        } catch (CloneNotSupportedException e) {
            Profile copy = new Profile();
            copy.privatePhone = this.privatePhone;
            return copy;
        }
     }

}
