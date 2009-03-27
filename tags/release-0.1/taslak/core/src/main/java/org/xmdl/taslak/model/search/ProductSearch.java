package org.xmdl.taslak.model.search;

import org.xmdl.taslak.model.ProductType;
import org.xmdl.taslak.model.Supplier;

public class ProductSearch {
    private String name;
    private Double fromPrice;
    private Double toPrice;
    private ProductType productType;
    private Supplier supplier;

    public ProductSearch() {
    }

    public ProductSearch(String name, Double fromPrice, Double toPrice,
			ProductType productType, Supplier supplier) {
		super();
		this.name = name;
		this.fromPrice = fromPrice;
		this.toPrice = toPrice;
		this.productType = productType;
		this.supplier = supplier;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(Double fromPrice) {
        this.fromPrice = fromPrice;
    }

    public Double getToPrice() {
        return toPrice;
    }

    public void setToPrice(Double toPrice) {
        this.toPrice = toPrice;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

	/**
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
    
    
}
