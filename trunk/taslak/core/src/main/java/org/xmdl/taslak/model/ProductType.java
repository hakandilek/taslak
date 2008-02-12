package org.xmdl.taslak.model;

public enum ProductType {

    RAWMATERIAL(1),
    INTERMEDIATEPRODUCT(2),
    PRODUCT(3),
    SERVICE(4);

    private int value;

    ProductType(int value) {
        this.value = value;
    }

    // the identifierMethod
    public int toInt() {
        return value;
    }

    public int getValue(){
        return value;
    }

    // the valueOfMethod
    public static ProductType fromInt(int value) {
        switch (value) {
            case 1:
                return RAWMATERIAL;
            case 2:
                return INTERMEDIATEPRODUCT;
            case 3:
                return PRODUCT;
            case 4:
                return SERVICE;
            default:
                return null;
        }
    }

    public String toString() {
        switch (this) {
            case RAWMATERIAL:
                return "Raw Material";
            case INTERMEDIATEPRODUCT:
                return "Intermediate Product";
            case PRODUCT:
                return "Product";
            case SERVICE:
                return "Service";
            default:
                return null;
        }
    }
}

