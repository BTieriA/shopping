package com.tieria.shopping.apis.product.controllers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.tieria.shopping.apis.product.containers.ProductDetailResultContainer;
import com.tieria.shopping.apis.product.containers.ProductListContainer;
import com.tieria.shopping.apis.product.containers.ProductResultContainer;
import com.tieria.shopping.apis.product.enums.ImageResult;
import com.tieria.shopping.apis.product.enums.ProductResult;
import com.tieria.shopping.apis.product.services.ProductService;
import com.tieria.shopping.apis.product.vos.AddImageVo;
import com.tieria.shopping.apis.product.vos.AddProductVo;
import com.tieria.shopping.apis.product.vos.ProductVo;
import com.tieria.shopping.common.Constant;
import com.tieria.shopping.common.Converter;
import com.tieria.shopping.common.UserVo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;


@RestController
@RequestMapping(
        value = "apis/product",
        method = {RequestMethod.GET, RequestMethod.POST},
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //    -------------------------------------------------------------------------------------------- CREATE (insert)
    // Insert Product
    @RequestMapping(value = "/addProduct")
    public String addProduct(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "brand", defaultValue = "") String brand,
                             @RequestParam(value = "name", defaultValue = "") String name,
                             @RequestParam(value = "price", defaultValue = "") String strPrice,
                             @RequestParam(value = "kinds", defaultValue = "") String kinds,
                             @RequestParam(value = "detail", defaultValue = "") String detail,
                             @RequestParam(value = "image") MultipartFile imageFile
    ) throws SQLException, IOException {
        // parsing
        int price = Converter.stringToInt(strPrice, -1);
        int intKinds = Converter.stringToInt(kinds, -1);
        byte[] imageFileBytes = imageFile.getBytes();

        // get user data
        UserVo userVo = (UserVo) request.getSession().getAttribute("UserVo");

        // upload image
        String fileName = imageFile.getOriginalFilename();
        try {
            String path = request.getSession().getServletContext().getRealPath("/uploads/" + fileName);
            File file = new File(path);
            if (!file.exists()) {
                // 폴더 생성
                file.mkdirs();
            }
            imageFile.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // insert product
        AddProductVo addProductVo = new AddProductVo(brand, name, price, intKinds, detail, fileName);
        ProductResultContainer productResultContainer = this.productService.addProduct(userVo, addProductVo);

        // insert image
        int index = productResultContainer.getIndex();
        AddImageVo addImageVo = new AddImageVo(index, name, imageFileBytes);
        ImageResult imageResult = this.productService.addImage(addImageVo);

        // result
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put(Constant.Common.PRODUCT_INPUT, productResultContainer.getProductResult().name().toLowerCase());
        jsonResponse.put(Constant.Common.IMAGE_UPLOAD, imageResult);

        return jsonResponse.toString(4);
    }

    //    -------------------------------------------------------------------------------------------- READ (select)
    // List - Total
    @RequestMapping(value = "/productList")
    public String productList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        ProductListContainer productListContainer = this.productService.getProductsList();
        JSONObject jsonResponse = new JSONObject();
        JSONArray jsonList = new JSONArray();

        if (productListContainer.getProductResult() == ProductResult.SUCCESS) {
            for (ProductVo product : productListContainer.getProductArray()) {
                JSONObject jsonItem = new JSONObject();
                jsonItem.put("itemBrand", product.getPdtBrand());
                jsonItem.put("itemName", product.getPdtName());
                jsonItem.put("itemPrice", product.getPdtPrice());
                jsonItem.put("itemKinds", product.getPdtKinds());
                jsonItem.put("itemDetail", product.getPdtDetail());
                jsonItem.put("itemIndex", product.getPdtIndex());

                jsonList.put(jsonItem);
            }
            jsonResponse.put("products", jsonList);
        } else {
            jsonResponse.put("products", "NoData");
        }
        return jsonResponse.toString(4);
    }

    // Get Image
    @RequestMapping(value = "/imageList", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] imageList(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(name = "index") String strIndex) throws SQLException,
            IOException {
        int index = Converter.stringToInt(strIndex, -1);
        if (index > 0) {
            return this.productService.getImage(index);
        } else {
            return null;
        }
    }

    // Get Detail
    @RequestMapping(value = "/detail")
    public String productDetail(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(name = "index") String strIndex) throws SQLException {

        int index = Converter.stringToInt(strIndex, -1);

        ProductDetailResultContainer productDetailResultContainer = this.productService.getDetail(index);

        JSONObject jsonResponse = new JSONObject();
        JSONObject jsonItem = new JSONObject();

        ProductVo product = productDetailResultContainer.getProductVo();

        if (productDetailResultContainer.getProductResult() == ProductResult.SUCCESS) {
            jsonItem.put("itemBrand", product.getPdtBrand());
            jsonItem.put("itemName", product.getPdtName());
            jsonItem.put("itemPrice", product.getPdtPrice());
            jsonItem.put("itemKinds", product.getPdtKinds());
            jsonItem.put("itemDetail", product.getPdtDetail());
            jsonItem.put("itemIndex", product.getPdtIndex());
            jsonResponse.put("product", jsonItem);
        } else {
            jsonResponse.put("product", "NoData");
        }
        return jsonResponse.toString(4);
    }

    // Get Related Product
    @RequestMapping(value = "/relate")
    public String relateProduct(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(name="kinds") String strKinds) throws SQLException, IOException {
        int kinds = Converter.stringToInt(strKinds, -1);
        ProductListContainer productListContainer = this.productService.getRelate(kinds);
        JSONObject jsonResponse = new JSONObject();
        JSONArray relateProducts = new JSONArray();
        if (productListContainer.getProductResult() == ProductResult.SUCCESS) {
            for(ProductVo relateProduct : productListContainer.getProductArray()) {
                JSONObject jsonItem = new JSONObject();
                jsonItem.put("itemBrand", relateProduct.getPdtBrand());
                jsonItem.put("itemName", relateProduct.getPdtName());
                jsonItem.put("itemPrice", relateProduct.getPdtPrice());
                jsonItem.put("itemKinds", relateProduct.getPdtKinds());
                jsonItem.put("itemDetail", relateProduct.getPdtDetail());
                jsonItem.put("itemIndex", relateProduct.getPdtIndex());
                relateProducts.put(jsonItem);
            }
            jsonResponse.put("relateProduct", relateProducts);
        } else {
            jsonResponse.put("relateProduct", "NoData");
        }
        return jsonResponse.toString(4);
    }

}
