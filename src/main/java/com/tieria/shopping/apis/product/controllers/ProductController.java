package com.tieria.shopping.apis.product.controllers;

import com.tieria.shopping.apis.product.containers.CartListContainer;
import com.tieria.shopping.apis.product.containers.ProductDetailResultContainer;
import com.tieria.shopping.apis.product.containers.ProductListContainer;
import com.tieria.shopping.apis.product.containers.ProductResultContainer;
import com.tieria.shopping.apis.product.enums.CartResult;
import com.tieria.shopping.apis.product.enums.ImageResult;
import com.tieria.shopping.apis.product.enums.ProductResult;
import com.tieria.shopping.apis.product.services.ProductService;
import com.tieria.shopping.apis.product.vos.*;
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

    // Insert Detail
    @RequestMapping(value = "/addDetail")
    public String addDetail(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(name = "color", defaultValue = "") String color,
                            @RequestParam(name = "size", defaultValue = "") String size,
                            @RequestParam(name = "productIndex", defaultValue = "") String productStrIndex,
                            @RequestParam(name = "imageIndex", defaultValue = "") String imgStrIndex)
            throws SQLException {
        // parsing
        int productIndex = Converter.stringToInt(productStrIndex, -1);
        int imgIndex = Converter.stringToInt(imgStrIndex, -1);
        // detail data
        UserVo userVo = (UserVo) request.getSession().getAttribute("UserVo");
        int userIndex = userVo.getUserIndex();
        AddColorSizeVo addColorSizeVo = new AddColorSizeVo(color, size, productIndex, imgIndex, userIndex);
        // insert detail
        ProductResult productResult = this.productService.addColorSize(addColorSizeVo, userVo);
        // result
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, productResult.name().toLowerCase());
        return jsonResponse.toString(4);
    }


    //    -------------------------------------------------------------------------------------------- READ (select)
    // List - Total
    @RequestMapping(value = "/sortList")
    public String sortList(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(name = "page", defaultValue = "1") String strPage,
                           @RequestParam(name = "sort_name", defaultValue = "") String sortName,
                           @RequestParam(name = "sort", defaultValue = "") String asc)
            throws SQLException, IOException {
        int page = Converter.stringToInt(strPage, 1);
        int totalCount = this.productService.getTotalCount();
        int maxPage = totalCount % 9 == 0 ? totalCount / 9 : (int) (Math.floor((double) totalCount / 9) + 1);
        int startPage = (page > 5) ? (page - 4) : 1;
        int endPage = (maxPage > 9) ? (page + 4) : maxPage;
        int intAsc = 0;
        if (asc.equals("asc")) {
            intAsc = 1;
        }
        ;
        SortVo sortVo = new SortVo(sortName, intAsc);

        ProductListContainer productListContainer = this.productService.getSortList(page, sortVo);
        JSONObject jsonResponse = new JSONObject();
        JSONArray jsonList = new JSONArray();

        if (page < 1 || productListContainer.getProductResult() == ProductResult.FAILURE) {
            jsonResponse.put("products", "NoData");
        } else {
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
            jsonResponse.put("page", page);
            jsonResponse.put("start_page", startPage);
            jsonResponse.put("end_page", endPage);
            jsonResponse.put("max_page", maxPage);
            jsonResponse.put("sort_name", sortName);
            jsonResponse.put("sort_asc", asc);
        }
        return jsonResponse.toString(4);
    }


    @RequestMapping(value = "/productList")
    public String productList(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(name = "page", defaultValue = "1") String strPage)
            throws SQLException, IOException {
        int page = Converter.stringToInt(strPage, 1);
        int totalCount = this.productService.getTotalCount();
        int maxPage = totalCount % 9 == 0 ? totalCount / 9 : (int) (Math.floor((double) totalCount / 9) + 1);
        int startPage = (page > 5) ? (page - 4) : 1;
        int endPage = (maxPage > 9) ? (page + 4) : maxPage;

        ProductListContainer productListContainer = this.productService.getProductsList(page);
        JSONObject jsonResponse = new JSONObject();
        JSONArray jsonList = new JSONArray();

        if (page < 1 || productListContainer.getProductResult() == ProductResult.FAILURE) {
            jsonResponse.put("products", "NoData");
        } else {
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
            jsonResponse.put("page", page);
            jsonResponse.put("start_page", startPage);
            jsonResponse.put("end_page", endPage);
            jsonResponse.put("max_page", maxPage);
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
                                @RequestParam(name = "kinds") String strKinds) throws SQLException, IOException {
        int kinds = Converter.stringToInt(strKinds, -1);
        ProductListContainer productListContainer = this.productService.getRelate(kinds);
        JSONObject jsonResponse = new JSONObject();
        JSONArray relateProducts = new JSONArray();
        if (productListContainer.getProductResult() == ProductResult.SUCCESS) {
            for (ProductVo relateProduct : productListContainer.getProductArray()) {
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

    // Get Color & Size
    @RequestMapping(value = "/colorSize")
    public String productColorSize(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        UserVo userVo = Converter.getUserVo(request);
        CartListContainer cartListContainer = this.productService.getColorSize(userVo);
        JSONObject jsonResponse = new JSONObject();
        JSONArray cartList = new JSONArray();
        if (cartListContainer.getCartResult() == CartResult.SUCCESS) {
            for (CartVo cart : cartListContainer.getCartList()) {
                JSONObject jsonItem = new JSONObject();
                jsonItem.put("itemIndex", cart.getImgIndex());
                jsonItem.put("itemName", cart.getItemName());
                jsonItem.put("itemBrand", cart.getItemBrand());
                jsonItem.put("itemPrice", cart.getItemPrice());
                jsonItem.put("itemColor", cart.getItemColor());
                jsonItem.put("itemSize", cart.getItemSize());
                jsonItem.put("itemDate", cart.getItemDate());
                cartList.put(jsonItem);
            }
            jsonResponse.put("cartList", cartList);
        } else
            jsonResponse.put("cartList", "NoData");
        return jsonResponse.toString(4);
    }

    // Get Latest Product
    @RequestMapping(value = "/latest")
    public String latestProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ProductListContainer productListContainer = this.productService.getLatest();
        JSONObject jsonResponse = new JSONObject();
        JSONArray latestProducts = new JSONArray();
        if (productListContainer.getProductResult() == ProductResult.SUCCESS) {
            for (ProductVo latestProduct : productListContainer.getProductArray()) {
                JSONObject jsonItem = new JSONObject();
                jsonItem.put("itemBrand", latestProduct.getPdtBrand());
                jsonItem.put("itemName", latestProduct.getPdtName());
                jsonItem.put("itemPrice", latestProduct.getPdtPrice());
                jsonItem.put("itemKinds", latestProduct.getPdtKinds());
                jsonItem.put("itemDetail", latestProduct.getPdtDetail());
                jsonItem.put("itemIndex", latestProduct.getPdtIndex());
                latestProducts.put(jsonItem);
            }
            jsonResponse.put("latestProduct", latestProducts);
        } else {
            jsonResponse.put("latestProduct", "NoData");
        }
        return jsonResponse.toString(4);
    }

    // Get Brand Product
    @RequestMapping(value = "/brand")
    public String BrandProduct(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(name = "brand", defaultValue = "") String brand,
                               @RequestParam(name = "kinds", defaultValue = "") String strKinds) throws SQLException {
        int kinds = Converter.stringToInt(strKinds, -1);
        BrandVo brandVo = new BrandVo(brand, kinds);
        ProductListContainer productListContainer = this.productService.getBrand(brandVo);
        JSONObject jsonResponse = new JSONObject();
        JSONArray brandProducts = new JSONArray();
        if (productListContainer.getProductResult() == ProductResult.SUCCESS) {
            for (ProductVo brandProduct : productListContainer.getProductArray()) {
                JSONObject jsonItem = new JSONObject();
                jsonItem.put("itemBrand", brandProduct.getPdtBrand());
                jsonItem.put("itemName", brandProduct.getPdtName());
                jsonItem.put("itemPrice", brandProduct.getPdtPrice());
                jsonItem.put("itemKinds", brandProduct.getPdtKinds());
                jsonItem.put("itemDetail", brandProduct.getPdtDetail());
                jsonItem.put("itemIndex", brandProduct.getPdtIndex());
                brandProducts.put(jsonItem);
            }
            jsonResponse.put("brandProduct", brandProducts);
        } else {
            jsonResponse.put("brandProduct", "NoData");
        }
        return jsonResponse.toString(4);
    }

    // Get Cart History
    @RequestMapping(value = "/history")
    public String cartHistory(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(name = "page", defaultValue = "1") String strPage) throws SQLException {
        int page = Converter.stringToInt(strPage, 1);
        int totalCount = this.productService.getTotalCarts();
        int maxPage = totalCount % 10 == 0 ? totalCount / 10 : (int) (Math.floor((double) totalCount / 10) + 1);
        int startPage = (page > 5) ? (page - 4) : 1;
        int endPage = (maxPage > 10) ? (page + 4) : maxPage;
        UserVo userVo = Converter.getUserVo(request);
        CartListContainer cartListContainer = this.productService.getCartHistory(userVo, page);
        JSONObject jsonResponse = new JSONObject();
        JSONArray cartList = new JSONArray();

        jsonResponse.put("page", page);
        jsonResponse.put("start_page", startPage);
        jsonResponse.put("end_page", endPage);
        jsonResponse.put("max_page", maxPage);

        if (cartListContainer.getCartResult() == CartResult.SUCCESS) {
            for (CartVo cart : cartListContainer.getCartList()) {
                JSONObject jsonItem = new JSONObject();
                jsonItem.put("itemIndex", cart.getImgIndex());
                jsonItem.put("itemName", cart.getItemName());
                jsonItem.put("itemBrand", cart.getItemBrand());
                jsonItem.put("itemPrice", cart.getItemPrice());
                jsonItem.put("itemColor", cart.getItemColor());
                jsonItem.put("itemSize", cart.getItemSize());
                jsonItem.put("itemDate", cart.getItemDate());
                cartList.put(jsonItem);
            }
            jsonResponse.put("cartList", cartList);
        } else if (cartListContainer.getCartResult() == CartResult.INVALID) {
            jsonResponse.put("cartList", "no_authorized");
        } else {
            jsonResponse.put("cartList", "no_data");
        }
        return jsonResponse.toString(4);
    }

    //    -------------------------------------------------------------------------------------------- UPDATE
    // Insert Product
    @RequestMapping(value = "/update")
    public String updateProduct(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value = "index", defaultValue = "") String strIndex,
                                @RequestParam(value = "brand", defaultValue = "") String brand,
                                @RequestParam(value = "name", defaultValue = "") String name,
                                @RequestParam(value = "price", defaultValue = "") String strPrice,
                                @RequestParam(value = "kinds", defaultValue = "") String kinds,
                                @RequestParam(value = "detail", defaultValue = "") String detail
    ) throws SQLException, IOException {
        // parsing
        int index = Converter.stringToInt(strIndex, -1);
        int price = Converter.stringToInt(strPrice, -1);
        int intKinds = Converter.stringToInt(kinds, -1);
        // get user data
        UserVo userVo = (UserVo) request.getSession().getAttribute("UserVo");
        // update product
        UpdateProductVo updateProductVo = new UpdateProductVo(index, brand, name, price, intKinds, detail);
        // result
        JSONObject jsonResponse = new JSONObject();
        if (index == -1 || price == -1 || intKinds == -1) {
            jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, "failure");
        } else {
            ProductResult productResult = this.productService.updateProduct(userVo, updateProductVo);
            jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, productResult.name().toLowerCase());
        }
        System.out.println("controller : " +  index);
        return jsonResponse.toString(4);
    }

    //    -------------------------------------------------------------------------------------------- DELETE
    @RequestMapping(value = "/deleteCart")
    public String deleteCart(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(name = "itemIndex", defaultValue = "") String strIndex) throws SQLException {
        int index = Converter.stringToInt(strIndex, -1);
        UserVo userVo = Converter.getUserVo(request);
        CartResult cartResult = this.productService.deleteCart(userVo, index);
        JSONObject jsonResponse = new JSONObject();
        if (cartResult == CartResult.SUCCESS) {
            jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, "success");
        } else if (cartResult == CartResult.INVALID) {
            jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, "invalid");
        } else {
            jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, "failure");
        }
        return jsonResponse.toString(4);
    }

    @RequestMapping(value = "/deleteAllCart")
    public String deleteAllCart(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        UserVo userVo = Converter.getUserVo(request);
        CartResult cartResult = this.productService.deleteAllCart(userVo);
        JSONObject jsonResponse = new JSONObject();
        if (cartResult == CartResult.SUCCESS) {
            jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, "success");
        } else if (cartResult == CartResult.INVALID) {
            jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, "invalid");
        } else {
            jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, "failure");
        }
        return jsonResponse.toString(4);
    }

    @RequestMapping(value = "/delete")
    public String deleteProduct(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(name = "index", defaultValue = "") String strIndex) throws SQLException {
        int index = Converter.stringToInt(strIndex, -1);
        UserVo userVo = Converter.getUserVo(request);
        ProductResult productResult = this.productService.deleteProduct(userVo, index);
        JSONObject jsonResponse = new JSONObject();
        if (productResult == ProductResult.SUCCESS) {
            jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, "success");
        } else if (productResult== ProductResult.INVALID) {
            jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, "invalid");
        } else {
            jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, "failure");
        }
        return jsonResponse.toString(4);
    }
}
