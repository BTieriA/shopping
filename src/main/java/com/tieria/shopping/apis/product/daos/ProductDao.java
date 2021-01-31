package com.tieria.shopping.apis.product.daos;

import com.tieria.shopping.apis.product.vos.*;
import com.tieria.shopping.common.UserVo;
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
                "(product_brand,\n" +
                " product_name,\n" +
                " product_price,\n" +
                " product_kinds,\n" +
                " product_detail,\n" +
                " product_image)\n" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, addProductVo.getPdtBrand());
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
            preparedStatement.execute();
        }
    }

    // insert color size
    public void insertDetail(Connection connection, AddColorSizeVo addColorSizeVo) throws SQLException {
        String query = "" +
                "INSERT INTO `shopping`.`details`\n" +
                "(detail_color,\n" +
                " detail_size,\n" +
                " product_index,\n" +
                " image_index," +
                " user_index)\n" +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, addColorSizeVo.getColor());
            preparedStatement.setString(2, addColorSizeVo.getSize());
            preparedStatement.setInt(3, addColorSizeVo.getItemIndex());
            preparedStatement.setInt(4, addColorSizeVo.getImgIndex());
            preparedStatement.setInt(5, addColorSizeVo.getUserIndex());
            preparedStatement.execute();
        }
    }

    // insert cart
    public void insertCart(Connection connection, AddColorSizeVo addColorSizeVo) throws SQLException {
        String query = "" +
                "INSERT INTO `shopping`.`carts`\n" +
                "(cart_color,\n" +
                " cart_size,\n" +
                " product_index,\n" +
                " image_index," +
                " user_index)\n" +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, addColorSizeVo.getColor());
            preparedStatement.setString(2, addColorSizeVo.getSize());
            preparedStatement.setInt(3, addColorSizeVo.getItemIndex());
            preparedStatement.setInt(4, addColorSizeVo.getImgIndex());
            preparedStatement.setInt(5, addColorSizeVo.getUserIndex());
            preparedStatement.execute();
        }
    }

    //    -------------------------------------------------------------------------------------------- READ (select)
    // Show products - Total
    public ArrayList<ProductVo> sortList(Connection connection, int page, SortVo sortVo) throws SQLException {
        ProductVo productVo = null;
        ArrayList<ProductVo> products = new ArrayList<>();
        String query = "" +
                "SELECT `product_index`  AS `itemIndex`,\n" +
                "       `product_brand`  AS `itemBrand`,\n" +
                "       `product_name`   AS `itemName`,\n" +
                "       `product_price`  AS `itemPrice`,\n" +
                "       `product_kinds`   AS `itemKinds`,\n" +
                "       `product_detail` AS `itemDetail`,\n" +
                "       `product_date` AS `itemDate`,\n" +
                "       `product_image`  AS `itemImage`\n" +
                "FROM `shopping`.`products`\n";

        if (sortVo.getSortName().equals("price")) {
            query += "ORDER BY `product_price`\n";
        } else if (sortVo.getSortName().equals("brand")) {
            query += "ORDER BY `product_brand`\n";
        } else {
            query += "ORDER BY `product_date`\n";
        }

        if (sortVo.getDesc() == 1) {
            query += "ASC limit ?, 9";
        } else {
            query += "DESC limit ?, 9";
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, (page - 1) * 9);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    productVo = new ProductVo(resultSet.getInt("itemIndex"),
                            resultSet.getString("itemBrand"),
                            resultSet.getString("itemName"),
                            resultSet.getInt("itemPrice"),
                            resultSet.getInt("itemKinds"),
                            resultSet.getString("itemDetail"),
                            resultSet.getDate("itemDate"),
                            resultSet.getString("itemImage"));
                    products.add(productVo);
                }
            }
        }
        return products;
    }

    public ArrayList<ProductVo> productsList(Connection connection, int page) throws SQLException {
        ProductVo productVo = null;
        ArrayList<ProductVo> products = new ArrayList<>();
        String query = "" +
                "SELECT `product_index`  AS `itemIndex`,\n" +
                "       `product_brand`  AS `itemBrand`,\n" +
                "       `product_name`   AS `itemName`,\n" +
                "       `product_price`  AS `itemPrice`,\n" +
                "       `product_kinds`   AS `itemKinds`,\n" +
                "       `product_detail` AS `itemDetail`,\n" +
                "       `product_date` AS `itemDate`,\n" +
                "       `product_image`  AS `itemImage`\n" +
                "FROM `shopping`.`products`\n" +
                "ORDER BY `product_index` DESC limit ?, 9";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, (page - 1) * 9);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    productVo = new ProductVo(resultSet.getInt("itemIndex"),
                            resultSet.getString("itemBrand"),
                            resultSet.getString("itemName"),
                            resultSet.getInt("itemPrice"),
                            resultSet.getInt("itemKinds"),
                            resultSet.getString("itemDetail"),
                            resultSet.getDate("itemDate"),
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

    // Get Total products
    public int getTotalProducts(Connection connection) throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(`product_index`) AS `count` FROM `shopping`.`products`";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    count = resultSet.getInt("count");
                }
            }
        }
        return count;
    }

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

    // Detail
    public ProductVo getDetail(Connection connection, int index) throws SQLException {
        ProductVo productVo = null;
        String query = "" +
                "SELECT `product_index`  AS `itemIndex`,\n" +
                "       `product_brand`  AS `itemBrand`,\n" +
                "       `product_name`   AS `itemName`,\n" +
                "       `product_price`  AS `itemPrice`,\n" +
                "       `product_kinds`  AS `itemKinds`,\n" +
                "       `product_detail` AS `itemDetail`,\n" +
                "       `product_date`   AS `itemDate`,\n" +
                "       `product_image`  AS `itemImage`\n" +
                "FROM `shopping`.`products`\n" +
                "WHERE `product_index` = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, index);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    productVo = new ProductVo(resultSet.getInt("itemIndex"),
                            resultSet.getString("itemBrand"),
                            resultSet.getString("itemName"),
                            resultSet.getInt("itemPrice"),
                            resultSet.getInt("itemKinds"),
                            resultSet.getString("itemDetail"),
                            resultSet.getDate("itemDate"),
                            resultSet.getString("itemImage"));
                }
            }
        }
        return productVo;
    }

    // Related Product
    public ArrayList<ProductVo> relatedProduct(Connection connection, int kinds) throws SQLException {
        ProductVo productVo = null;
        ArrayList<ProductVo> products = new ArrayList<>();
        String query = "" +
                "SELECT `product_index`  AS `itemIndex`,\n" +
                "       `product_brand`  AS `itemBrand`,\n" +
                "       `product_name`   AS `itemName`,\n" +
                "       `product_price`  AS `itemPrice`,\n" +
                "       `product_kinds`  AS `itemKinds`,\n" +
                "       `product_detail` AS `itemDetail`,\n" +
                "       `product_date`   AS `itemDate`,\n" +
                "       `product_image`  AS `itemImage`\n" +
                "FROM `shopping`.`products`\n" +
                "WHERE `product_kinds` = ? \n" +
                "ORDER BY `product_date` DESC \n" +
                "LIMIT 4";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, kinds);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    productVo = new ProductVo(resultSet.getInt("itemIndex"),
                            resultSet.getString("itemBrand"),
                            resultSet.getString("itemName"),
                            resultSet.getInt("itemPrice"),
                            resultSet.getInt("itemKinds"),
                            resultSet.getString("itemDetail"),
                            resultSet.getDate("itemDate"),
                            resultSet.getString("itemImage"));
                    products.add(productVo);
                }
            }
        }
        return products;
    }

    // color size data
    public ArrayList<CartVo> getColorSize(Connection connection, UserVo userVo) throws SQLException {
        CartVo cartVo = null;
        ArrayList<CartVo> cartList = new ArrayList<>();
        String query = "" +
                "SELECT `details`.`image_index` AS `imgIndex`,\n" +
                "       `product_name`          AS `itemName`,\n" +
                "       `product_brand`         AS `itemBrand`,\n" +
                "       `product_price`         AS `itemPrice`,\n" +
                "       `detail_color`          AS `itemColor`,\n" +
                "       `detail_size`           AS `itemSize`,\n" +
                "       `details`.`user_index`  AS `userIndex`,\n" +
                "       `detail_date`           AS `itemDate`\n" +
                "FROM `shopping`.`details`\n" +
                "         INNER JOIN `shopping`.`products`\n" +
                "                    ON `details`.`product_index` = `products`.`product_index`\n" +
                "         INNER JOIN `shopping`.`images`\n" +
                "                    ON `details`.`image_index` = `images`.`image_index`\n" +
                "         INNER JOIN `shopping`.`users`\n" +
                "                    ON `details`.`user_index` = `users`.`user_index`\n" +
                "WHERE `details`.`user_index` = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userVo.getUserIndex());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    cartVo = new CartVo(resultSet.getInt("imgIndex"),
                            resultSet.getString("itemName"),
                            resultSet.getString("itemBrand"),
                            resultSet.getString("itemColor"),
                            resultSet.getString("itemSize"),
                            resultSet.getInt("itemPrice"),
                            resultSet.getTimestamp("itemDate"));
                    cartList.add(cartVo);
                }
            }
        }
        return cartList;
    }

    //    -------------------------------------------------------------------------------------------- DELETE
    // delete cart list
    public int deleteCart(Connection connection, int index) throws SQLException {
        int deleteResult = 0;
        String query = "" +
                "DELETE\n" +
                "FROM `shopping`.`details`\n" +
                "WHERE product_index = ? \n" +
                "ORDER BY `detail_date` DESC \n" +
                "LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, index);
            deleteResult = preparedStatement.executeUpdate();
        }
        return deleteResult;

    }

    // delete cart final list
    public int deleteFinalCart(Connection connection, int index) throws SQLException {
        int deleteResult = 0;
        String query = "" +
                "DELETE\n" +
                "FROM `shopping`.`carts`\n" +
                "WHERE product_index = ? \n" +
                "ORDER BY `cart_date` DESC \n" +
                "LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, index);
            deleteResult = preparedStatement.executeUpdate();
        }
        return deleteResult;

    }

    // delete cart all list
    public int deleteAllCart(Connection connection) throws SQLException {
        int deleteResult = 0;
        String query = "" +
                "DELETE\n" +
                "FROM `shopping`.`details`\n";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            deleteResult = preparedStatement.executeUpdate();
        }
        return deleteResult;
    }


}
