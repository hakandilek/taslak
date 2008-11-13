package org.xmdl.taslak;

import java.util.Set;

import org.xmdl.mojo.meta.Id;
import org.xmdl.mojo.meta.ManyToMany;

public class Supplier {
	@Id
	Long id;

	String name;

	@ManyToMany(target = "suppliers")
	Set<Product> products;
}
