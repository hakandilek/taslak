package org.xmdl.taslak;

import org.xmdl.mojo.annotation.Id;
import org.xmdl.mojo.annotation.ManyToOne;
import org.xmdl.mojo.annotation.Mojo;

@Mojo
public class OrderElement {
	@Id
	Long id;

	Long quantity;

	@ManyToOne(target = "orderElements")
	Order order;

	@ManyToOne(target = "orderElements")
	Product product;
}
