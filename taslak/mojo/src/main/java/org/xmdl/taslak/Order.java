package org.xmdl.taslak;

import java.util.Date;
import java.util.Set;

import org.xmdl.mojo.annotation.AssociationBehaviour;
import org.xmdl.mojo.annotation.Id;
import org.xmdl.mojo.annotation.Mojo;
import org.xmdl.mojo.annotation.OneToMany;

@Mojo
public class Order {
	@Id
	Long id;

	String name;

	Date createDate;

	Double priceTotals;

	@OneToMany(target = "order", behaviour = AssociationBehaviour.COMPOSITION)
	Set<OrderElement> orderElements;
}
