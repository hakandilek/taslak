package org.xmdl.taslak;

import java.util.Set;

import org.xmdl.mojo.annotation.Id;
import org.xmdl.mojo.annotation.ManyToMany;
import org.xmdl.mojo.annotation.Mojo;
import org.xmdl.mojo.annotation.OneToMany;

@Mojo
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
