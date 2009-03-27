package org.xmdl.taslak;

import org.xmdl.mesken.model.User;
import org.xmdl.mojo.annotation.Id;
import org.xmdl.mojo.annotation.Mojo;

@Mojo
public class Profile {
	@Id
	Long id;

	User user;

	String privatePhone;
}
