package org.xmdl.taslak.model.search;

import org.xmdl.taslak.model.Product;

public class ProductSearch {
    Product fromProduct;
    Product toProduct;

    public ProductSearch() {
    }

    public Product getFromProduct() {
        return fromProduct;
    }

    public void setFromProduct(Product fromProduct) {
        this.fromProduct = fromProduct;
    }

    public Product getToProduct() {
        return toProduct;
    }

    public void setToProduct(Product toProduct) {
        this.toProduct = toProduct;
    }
}
