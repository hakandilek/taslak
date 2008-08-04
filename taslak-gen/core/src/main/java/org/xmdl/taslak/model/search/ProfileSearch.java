
package org.xmdl.taslak.model.search;


import java.io.Serializable;

import java.text.MessageFormat;

import java.util.*;

import org.xmdl.mesken.model.User;

import org.xmdl.taslak.model.*; 







/**
 *
 * Profile entity search bean
 *  
 * $Id$
 *
 * @generated
 */ 
public class ProfileSearch implements Serializable {

    /** 
     * @generated
     */
    private String privatePhone;
    

    /** 
     * @generated
     */
    private User user;


    /** 
     * @generated
     */
    public ProfileSearch() {
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
	@Override
    public String toString() {
        return MessageFormat.format("ProfileSearch "
            + "[privatePhone={0}]"
            + "[user={1}]"
            , privatePhone
            , user
        );
    }

    /** 
     * @generated
     */
	@Override
    public int hashCode() {
        int result = 1;
        if (privatePhone != null) result = 31 * result + privatePhone.hashCode();
        if (user != null) result = 31 * result + user.hashCode();
        return result;
    }

	@Override
	public boolean equals(Object obj) {
		return hashCode() == obj.hashCode();
	}

}
