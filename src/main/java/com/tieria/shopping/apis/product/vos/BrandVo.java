package com.tieria.shopping.apis.product.vos;

public class BrandVo {
    private final String brand;
    private final int kinds;

    public BrandVo(String brand, int kinds) {
        this.brand = brand;
        this.kinds = kinds;
    }

    public String getBrand() {
        return brand;
    }

    public int getKinds() {
        return kinds;
    }
}
