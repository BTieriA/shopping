package com.tieria.shopping.apis.product.containers;

import com.tieria.shopping.apis.product.enums.ProductResult;
import com.tieria.shopping.apis.product.vos.ProductVo;

import java.util.ArrayList;

public class ProductListContainer {
    private final ProductResult productResult;
    private final ArrayList<ProductVo> productList;

    public ProductListContainer(ProductResult productResult) {
        this.productResult = productResult;
        this.productList = null;
    }
    public ProductListContainer(ProductResult productResult, ArrayList<ProductVo> productList) {
        this.productResult = productResult;
        this.productList = productList;
    }

    public ProductResult getProductResult() {
        return productResult;
    }

    public ArrayList<ProductVo> getproductList() {
        return productList;
    }
}
