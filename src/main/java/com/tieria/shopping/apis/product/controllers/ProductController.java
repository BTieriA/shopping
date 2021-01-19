package com.tieria.shopping.apis.product.controllers;

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
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
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
    @RequestMapping(value = "/addProduct")
    public String addProduct(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "room", defaultValue = "") String room,
                             @RequestParam(value = "name", defaultValue = "") String name,
                             @RequestParam(value = "price", defaultValue = "") String strPrice,
                             @RequestParam(value = "kinds", defaultValue = "") String kinds,
                             @RequestParam(value = "detail", defaultValue = "") String detail,
                             @RequestParam(value = "image") MultipartFile imageFile
    ) throws SQLException, IOException {
        // parsing
        int price = Converter.stringToInt(strPrice, -1);
        int intKinds = Converter.stringToInt(strPrice, -1);
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
        AddProductVo addProductVo = new AddProductVo(room, name, price, intKinds, detail, fileName);
        ProductResultContainer productResultContainer = this.productService.addProduct(userVo, addProductVo);

        // insert image
        int index = productResultContainer.getIndex();
        AddImageVo addImageVo = new AddImageVo(index, name, imageFileBytes);
        ImageResult imageResult = this.productService.addImage(addImageVo);

        //        String imageData = Converter.imageToString(imageFile);
//        AddImageVo addImageVo = new AddImageVo(index, name, imageData);
//        ImageResult imageResult = this.productService.addImage(addImageVo);

        // result
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put(Constant.Common.PRODUCT_INPUT, productResultContainer.getProductResult().name().toLowerCase());
        jsonResponse.put(Constant.Common.IMAGE_UPLOAD, imageResult);

        return jsonResponse.toString(4);
    }

    //    -------------------------------------------------------------------------------------------- READ (select)
    @RequestMapping(value = "/productList")
    public String productList(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        ProductListContainer productListContainer = this.productService.getProductsList();
        JSONObject jsonResponse = new JSONObject();
        JSONArray jsonList = new JSONArray();

        if (productListContainer.getProductResult() == ProductResult.SUCCESS) {
            for (ProductVo product : productListContainer.getproductList()) {
                JSONObject jsonItem = new JSONObject();
                jsonItem.put("itemRoom", product.getPdtRoom());
                jsonItem.put("itemName", product.getPdtName());
                jsonItem.put("itemPrice", product.getPdtPrice());
                jsonItem.put("itemKinds", product.getPdtKinds());
                jsonItem.put("itemDetail", product.getPdtDetail());
                jsonItem.put("itemIndex", product.getPdtIndex());

                // byte[] -> json
//                int index = product.getPdtIndex();
//                byte[] imageOutput = this.productService.getImage(index);
//                byte[] imageBase64 = Base64Utils.encode(imageOutput);
//                jsonItem.put("itemImage", imageBase64);

                // image 출력
//                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageOutput);
//                BufferedImage bufferedImageInput = ImageIO.read(byteArrayInputStream);
//                ImageIO.write(bufferedImageInput, "jpg", new File(product.getPdtName()));

                // Container -> JSON
//                ProductIndexVo indexVo = new ProductIndexVo(product.getPdtIndex());
//                ImageListContainer imageListContainer = this.productService.getImagesList(indexVo);
//                byte[] image = imageListContainer.getImageData();
//                jsonItem.put("itemImage", image);
                jsonList.put(jsonItem);
            }
            jsonResponse.put("products", jsonList);
        }
        return jsonResponse.toString(4);
    }

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

}
