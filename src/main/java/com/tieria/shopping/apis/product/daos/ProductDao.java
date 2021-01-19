package com.tieria.shopping.apis.product.daos;

import com.tieria.shopping.apis.product.vos.*;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class ProductDao {
    //    -------------------------------------------------------------------------------------------- CREATE (insert)
    // insert product
    public void insertProduct(Connection connection, AddProductVo addProductVo) throws SQLException {
        String query = "" +
                "INSERT INTO `shopping`.`products`\n" +
                "(product_room,\n" +
                " product_name,\n" +
                " product_price,\n" +
                " product_kinds,\n" +
                " product_detail,\n" +
                " product_image)\n" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, addProductVo.getPdtRoom());
            preparedStatement.setString(2, addProductVo.getPdtName());
            preparedStatement.setInt(3, addProductVo.getPdtPrice());
            preparedStatement.setInt(4, addProductVo.getPdtKinds());
            preparedStatement.setString(5, addProductVo.getPdtDetail());
            preparedStatement.setString(6, addProductVo.getPdtImage());
            preparedStatement.execute();
        }
    }

    // insert image
    public void insertImage(Connection connection, AddImageVo addImageVo) throws SQLException {
        String query = "" +
                "INSERT INTO `shopping`.`images`\n" +
                "(`product_index`,\n" +
                " `image_name`,\n" +
                " `image_data`)\n" +
                "VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, addImageVo.getProductIndex());
            preparedStatement.setString(2, addImageVo.getImageName());
            preparedStatement.setBytes(3, addImageVo.getImageData());
//            preparedStatement.setString(3, addImageVo.getImageData());
            preparedStatement.execute();
        }
    }

    //    -------------------------------------------------------------------------------------------- READ (select)
    // get product last index
    public int getLastIndex(Connection connection) throws SQLException {
        int index = -1;
        String query = "SELECT LAST_INSERT_ID() AS `index`";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    index = resultSet.getInt("index");
                }
            }
        }
        return index;
    }

    // select products
    public ArrayList<ProductVo> productsList(Connection connection) throws SQLException {
        ProductVo productVo = null;
        ArrayList<ProductVo> products = new ArrayList<>();
        String query = "" +
                "SELECT `product_index`  AS `itemIndex`,\n" +
                "       `product_room`   AS `itemRoom`,\n" +
                "       `product_name`   AS `itemName`,\n" +
                "       `product_price`  AS `itemPrice`,\n" +
                "       `product_kinds`   AS `itemKinds`,\n" +
                "       `product_detail` AS `itemDetail`,\n" +
                "       `product_image`  AS `itemImage`\n" +
                "FROM `shopping`.`products`";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    productVo = new ProductVo(resultSet.getInt("itemIndex"),
                            resultSet.getString("itemRoom"),
                            resultSet.getString("itemName"),
                            resultSet.getInt("itemPrice"),
                            resultSet.getInt("itemKinds"),
                            resultSet.getString("itemDetail"),
                            resultSet.getString("itemImage"));
                    products.add(productVo);
                }
            }
        }
        return products;
    }


    // Get Image Test1 - image -> byte[]에 저장
    public byte[] getImage(Connection connection, int index) throws SQLException {
        byte[] imageByte = null;
        String query = "" +
                "SELECT `image_data`             AS `imgData`\n" +
                "FROM `shopping`.`images`\n" +
                "         INNER JOIN `shopping`.`products`\n" +
                "                    ON `images`.`product_index` = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, index);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    imageByte = resultSet.getBytes("imgData");
                }
            }
        }
        return imageByte;
    }

    // Get Image Test2 - image 를 행렬에 저장
//    public ArrayList<ImageVo> getImage(Connection connection, ProductIndexVo productIndexVo) throws SQLException {
//        ImageVo imageVo = null;
//        ArrayList<ImageVo> images = new ArrayList<>();
//        String query = "" +
//                "SELECT `image_index`             AS `imgIndex`,\n" +
//                "       `images`.`product_index` AS `pdtIndex`,\n" +
//                "       `image_name`             AS `imgName`,\n" +
//                "       `image_data`             AS `imgData`\n" +
//                "FROM `shopping`.`images`\n" +
//                "         INNER JOIN `shopping`.`products`\n" +
//                "                    ON `images`.`product_index` = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1, productIndexVo.getProductIndex());
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    imageVo = new ImageVo(
//                            resultSet.getInt("imgIndex"),
//                            resultSet.getInt("pdtIndex"),
//                            resultSet.getString("imgName"),
//                            resultSet.getBytes("imgData"));
//                    images.add(imageVo);
//                }
//            }
//        }
//        return images;
//    }

    // Get Image Test 3 - Image -> String
//    public String getImage(Connection connection, int index) throws SQLException {
//        String imageData = null;
//        String query = "" +
//                "SELECT `image_data`             AS `imgData`\n" +
//                "FROM `shopping`.`images`\n" +
//                "         INNER JOIN `shopping`.`products`\n" +
//                "                    ON `images`.`product_index` = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1, index);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    imageData =resultSet.getString("imgData");
//                }
//            }
//        }
//        return imageData;
//    }

}
