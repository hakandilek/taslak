package org.xmdl.taslak.model;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.xmdl.ida.lib.model.BaseObject;

@SuppressWarnings("serial")
@Entity(name = ("t_supplier"))
public class Supplier extends BaseObject implements Serializable {

	private Long id;
	private String name;
	private Set<Product> products = new HashSet<Product>();

	public Supplier() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = ("id"))
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = ("name"), length = 63, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "suppliers", targetEntity = Product.class)
	public Set<Product> getProducts() {
		return products;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public String toString() {
		return MessageFormat.format("Supplier [id={0}][name={1}]", id, name);
	}

	public boolean equals(Object o) {
		return o instanceof Supplier && ((Supplier) o).getId() == this.getId();
	}

	@Override
	public int hashCode() {
		int result;
		result = id.hashCode();
		result = 31 * result + name.hashCode();
		return result;
	}

}
