package com.tieria.shopping.apis.product.containers;

import com.tieria.shopping.apis.product.enums.ProductResult;
import com.tieria.shopping.apis.product.vos.ProductVo;

import java.util.ArrayList;

public class ProductListContainer {
    private final ProductResult productResult;
    private final ArrayList<ProductVo> productArray;

    public ProductListContainer(ProductResult productResult) {
        this.productResult = productResult;
        this.productArray = null;
    }

    public ProductListContainer(ProductResult productResult, ArrayList<ProductVo> productArray) {
        this.productResult = productResult;
        this.productArray = productArray;
    }

    public ProductResult getProductResult() {
        return productResult;
    }

    public ArrayList<ProductVo> getProductArray() {
        return productArray;
    }
}
