package com.tieria.shopping.apis.product.daos;

import com.tieria.shopping.apis.product.vos.AddImageVo;
import com.tieria.shopping.apis.product.vos.AddProductVo;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                " product_size,\n" +
                " product_detail,\n" +
                " product_image)\n" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, addProductVo.getPdtRoom());
            preparedStatement.setString(2, addProductVo.getPdtName());
            preparedStatement.setInt(3, addProductVo.getPdtPrice());
            preparedStatement.setString(4, addProductVo.getPdtSize());
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
}
