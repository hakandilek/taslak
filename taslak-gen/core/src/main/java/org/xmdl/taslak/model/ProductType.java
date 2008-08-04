
package org.xmdl.taslak.model;

 


/**
 *
 * ProductType enumeration type
 *  
 * $Id$
 *
 * @generated
 */ 
public enum ProductType {

    RAWMATERIAL(1),
    INTERMEDIATEPRODUCT(2),
    PRODUCT(3),
    SERVICE(4),
    ;

    /**
     * @generated
     */ 
    private int value;

    ProductType(int value) {
        this.value = value;
    }

    /**
     * The identifier method
     * @generated
     */ 
    public int toInt() {
        return value;
    }

    /**
     * @generated
     */ 
    public int getValue(){
        return value;
    }

    /**
     * the value Of Method
     * @generated
     */ 
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

    /**
     * @generated
     */ 
    public String toString() {
        switch (this) {
            case RAWMATERIAL:
                return "RAWMATERIAL";
            case INTERMEDIATEPRODUCT:
                return "INTERMEDIATEPRODUCT";
            case PRODUCT:
                return "PRODUCT";
            case SERVICE:
                return "SERVICE";
            default:
                return null;
        }
    }
}


