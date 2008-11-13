package org.xmdl.taslak;

import java.util.Set;

import org.xmdl.mojo.meta.Id;
import org.xmdl.mojo.meta.ManyToMany;
import org.xmdl.mojo.meta.OneToMany;

public class Product {
	@Id
	Long id;

	String name;

	Money price;

	@OneToMany(target = "product")
	Set<OrderElement> orderElements;

	@ManyToMany(target = "suppliers")
	Set<Supplier> suppliers;
}
