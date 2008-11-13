package org.xmdl.taslak;

import org.xmdl.mesken.model.User;
import org.xmdl.mojo.meta.Id;

public class Profile {
	@Id
	Long id;

	User user;

	String privatePhone;
}
