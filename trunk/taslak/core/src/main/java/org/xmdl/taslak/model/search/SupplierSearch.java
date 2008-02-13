package org.xmdl.taslak.model.search;

import org.xmdl.taslak.model.Product;


public class SupplierSearch {
    private String name;
    private Product product;

    public SupplierSearch() {
    }

    public SupplierSearch(String name, Product product) {
		super();
		this.name = name;
		this.product = product;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
}
