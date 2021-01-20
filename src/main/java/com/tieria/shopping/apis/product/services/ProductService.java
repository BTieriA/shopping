package com.tieria.shopping.apis.product.services;

import com.tieria.shopping.apis.product.containers.ImageListContainer;
import com.tieria.shopping.apis.product.containers.ProductListContainer;
import com.tieria.shopping.apis.product.containers.ProductResultContainer;
import com.tieria.shopping.apis.product.daos.ProductDao;
import com.tieria.shopping.apis.product.enums.ImageResult;
import com.tieria.shopping.apis.product.enums.ProductResult;
import com.tieria.shopping.apis.product.vos.*;
import com.tieria.shopping.common.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.sql.DataSource;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ProductService {
    private final DataSource dataSource;
    private final ProductDao productDao;

    @Autowired
    public ProductService(DataSource dataSource, ProductDao productDao) {
        this.dataSource = dataSource;
        this.productDao = productDao;
    }

    //    -------------------------------------------------------------------------------------------- CREATE (insert)
    public ProductResultContainer addProduct(UserVo userVo, AddProductVo addProductVo) throws SQLException {
        int index = -1;
        if(addProductVo.getPdtPrice() < 0){
            return new ProductResultContainer(ProductResult.INVALID);
        }
        if(userVo == null || (userVo.getUserLevel() != 1)){
            return new ProductResultContainer(ProductResult.NOT_ALLOWED);
        }
        try (Connection connection = this.dataSource.getConnection()) {
            this.productDao.insertProduct(connection, addProductVo);
            index = this.productDao.getLastIndex(connection);
            if (index < 0) {
                return new ProductResultContainer(ProductResult.INVALID);
            } else {
                return new ProductResultContainer(ProductResult.SUCCESS, index);
            }
        }
    }

    public ImageResult addImage(AddImageVo addImageVo) throws SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            if (addImageVo.getProductIndex() < 0) {
                return ImageResult.IMAGE_UPLOAD_FAILURE;
            } else {
                this.productDao.insertImage(connection, addImageVo);
                return ImageResult.IMAGE_UPLOAD_SUCCESS;
            }
        }
    }

    //    -------------------------------------------------------------------------------------------- READ (select)
    // List - Total
    public ProductListContainer getProductsList() throws SQLException {
        try(Connection connection = this.dataSource.getConnection()) {
            ArrayList<ProductVo> itemList = new ArrayList<>();
            ArrayList<ProductVo> products = this.productDao.productsList(connection);
            if ( products != null) {
                for (ProductVo item : products) {
                    itemList.add(new ProductVo(
                            item.getPdtIndex(),
                            item.getPdtBrand(),
                            item.getPdtName(),
                            item.getPdtPrice(),
                            item.getPdtKinds(),
                            item.getPdtDetail(),
                            item.getPdtDate(),
                            item.getPdtImage()
                    ));
                }
                return new ProductListContainer(ProductResult.SUCCESS, itemList);
            } else {
                return new ProductListContainer(ProductResult.FAILURE, null);
            }

        }
    }

    // Get Image Test 3 - Image(byte[]) -> byte[]
    public byte[] getImage(int index) throws SQLException, IOException {
        try (Connection connection = this.dataSource.getConnection()) {
            byte[] imageOutput = null;

            byte[] imagesDao = this.productDao.getImage(connection, index);

            BufferedImage bufferedImageDao = ImageIO.read(new ByteArrayInputStream(imagesDao));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImageDao, "jpg", byteArrayOutputStream);
            imageOutput = byteArrayOutputStream.toByteArray();

            return imageOutput;
        }
    }

}
