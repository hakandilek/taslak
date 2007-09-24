package org.xmdl.taslak.model.search;

import org.xmdl.taslak.model.Product;

public class ProductSearch {
    private String name;
    private Double fromPrice;
    private Double toPrice;

    public ProductSearch() {
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

}
