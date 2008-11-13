package org.xmdl.taslak;

import org.xmdl.mojo.meta.Id;
import org.xmdl.mojo.meta.ManyToOne;

public class OrderElement {
	@Id
	Long id;

	Long quantity;

	@ManyToOne(target = "orderElements")
	Order order;

	@ManyToOne(target = "orderElements")
	Product product;
}
