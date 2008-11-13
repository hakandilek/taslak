package org.xmdl.taslak;

import java.util.Date;
import java.util.Set;

import org.xmdl.mojo.meta.AssociationBehaviour;
import org.xmdl.mojo.meta.Id;
import org.xmdl.mojo.meta.OneToMany;

public class Order {
	@Id
	Long id;

	String name;

	Date createDate;

	Double priceTotals;

	@OneToMany(target = "order", behaviour = AssociationBehaviour.COMPOSITION)
	Set<OrderElement> orderElements;
}
