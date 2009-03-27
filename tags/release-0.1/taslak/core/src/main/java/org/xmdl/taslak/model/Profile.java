package org.xmdl.taslak.model;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.xmdl.ida.lib.model.BaseObject;
import org.xmdl.mesken.model.User;

@SuppressWarnings("serial")
@Entity(name = ("t_profile"))
public class Profile extends BaseObject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ("id"))
    private Long id;
    
    
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;
    
    @Column(name = ("privatePhone"), length = 500, nullable = false)
    private String privatePhone;

    public Profile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}


	/**
	 * @return the privatePhone
	 */
	public String getPrivatePhone() {
		return privatePhone;
	}

	/**
	 * @param privatePhone the privatePhone to set
	 */
	public void setPrivatePhone(String privatePhone) {
		this.privatePhone = privatePhone;
	}

	public String toString() {
        return MessageFormat.format("Profile [id={0}][privatePhone={1}]", id, privatePhone);
    }

    public boolean equals(Object o) {
        return o instanceof Profile && ((Profile) o).getId() == this.getId();
    }

    public int hashCode() {
        int result;
        result = id.hashCode();
        result = 31 * result + privatePhone.hashCode();
        if (user != null)
        	result = 31 * result + user.hashCode();
        return result;
    }

}
