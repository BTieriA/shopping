package com.tieria.shopping.apis.product.containers;

import com.tieria.shopping.apis.product.enums.ProductResult;

public class ProductResultContainer {
    private final ProductResult productResult;
    private final int index;

    public ProductResultContainer(ProductResult productResult) {
        this(productResult, -1);
    }

    public ProductResultContainer(ProductResult productResult, int index) {
        this.productResult = productResult;
        this.index = index;
    }

    public ProductResult getProductResult() {
        return productResult;
    }

    public int getIndex() {
        return index;
    }
}
