package org.xmdl.taslak;

import java.util.Set;

import org.xmdl.mojo.annotation.Id;
import org.xmdl.mojo.annotation.ManyToMany;
import org.xmdl.mojo.annotation.Mojo;

@Mojo
public class Supplier {
	@Id
	Long id;

	String name;

	@ManyToMany(target = "suppliers")
	Set<Product> products;
}
