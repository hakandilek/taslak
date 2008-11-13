package org.xmdl.taslak;

public enum ProductType {

    RAWMATERIAL(1),
    INTERMEDIATEPRODUCT(2),
    PRODUCT(3),
    SERVICE(4);

    int value;

    ProductType(int value) {
        this.value = value;
    }
}

