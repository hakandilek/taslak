package org.xmdl.taslak.model.search;

import org.xmdl.mesken.model.User;

public class ProfileSearch {
	private User user;
	private String privatePhone;

	public ProfileSearch() {
	}

	public ProfileSearch(User user, String privatePhone) {
		super();
		this.user = user;
		this.privatePhone = privatePhone;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
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
	 * @param privatePhone
	 *            the privatePhone to set
	 */
	public void setPrivatePhone(String privatePhone) {
		this.privatePhone = privatePhone;
	}

}
