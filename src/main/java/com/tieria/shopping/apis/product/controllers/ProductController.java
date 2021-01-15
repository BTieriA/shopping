package com.tieria.shopping.apis.product.controllers;

import com.tieria.shopping.apis.product.containers.ProductResultContainer;
import com.tieria.shopping.apis.product.enums.ImageResult;
import com.tieria.shopping.apis.product.services.ProductService;
import com.tieria.shopping.apis.product.vos.AddImageVo;
import com.tieria.shopping.apis.product.vos.AddProductVo;
import com.tieria.shopping.common.Constant;
import com.tieria.shopping.common.Converter;
import com.tieria.shopping.common.UserVo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.util.Arrays;


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
    @RequestMapping(value="/addProduct")
    public String addProduct(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "room", defaultValue = "") String room,
                             @RequestParam(value = "name", defaultValue = "") String name,
                             @RequestParam(value = "price", defaultValue = "") String strPrice,
                             @RequestParam(value = "size", defaultValue = "") String size,
                             @RequestParam(value = "detail", defaultValue = "") String detail,
                             @RequestParam(value = "image") MultipartFile imageFile
    ) throws SQLException, IOException {
        // parsing
        Integer price = Converter.stringToInt(strPrice, -1);
        byte[] imageFileBytes = imageFile.getBytes();

        // get user data
        UserVo userVo = (UserVo) request.getSession().getAttribute("UserVo");

        // upload image
        String fileName = imageFile.getOriginalFilename();
        try {
            String path = request.getSession().getServletContext().getRealPath("/uploads/" + fileName);
            File file = new File(path);
            if(!file.exists()) {
                // 폴더 생성
                file.mkdirs();
            }
            imageFile.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // insert product
        AddProductVo addProductVo = new AddProductVo(room, name, price, size, detail, fileName);
        ProductResultContainer productResultContainer = this.productService.addProduct(userVo, addProductVo);

        // insert image
        int index = productResultContainer.getIndex();
        AddImageVo addImageVo = new AddImageVo(index, name, imageFileBytes);
        ImageResult imageResult = this.productService.addImage(addImageVo);

        // result
        JSONObject jsonUploadImage = new JSONObject();
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put(Constant.Common.PRODUCT_INPUT, productResultContainer.getProductResult().name().toLowerCase());
        jsonUploadImage.put(Constant.Common.IMAGE_UPLOAD, imageResult.name().toLowerCase());

        return jsonResponse.toString(4);
    }

}
