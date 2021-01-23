package com.tieria.shopping.apis.product.containers;

import com.tieria.shopping.apis.product.enums.ProductResult;
import com.tieria.shopping.apis.product.vos.ProductVo;

public class ProductDetailResultContainer {
    private final ProductResult productResult;
    private final ProductVo productVo;

    public ProductDetailResultContainer(ProductResult productResult, ProductVo productVo) {
        this.productResult = productResult;
        this.productVo = productVo;
    }

    public ProductResult getProductResult() {
        return productResult;
    }

    public ProductVo getProductVo() {
        return productVo;
    }
}
