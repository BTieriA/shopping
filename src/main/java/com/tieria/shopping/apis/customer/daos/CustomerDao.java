package com.tieria.shopping.apis.customer.daos;

import com.tieria.shopping.apis.customer.vos.InsertQnaVo;
import com.tieria.shopping.apis.customer.vos.QnaVo;
import com.tieria.shopping.common.UserVo;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class CustomerDao {
    //    -------------------------------------------------------------------------------------------- CREATE (insert)
    public void insertQna(Connection connection, InsertQnaVo insertQnaVo) throws SQLException {
        String query = "" +
                "INSERT INTO `shopping`.`customers`\n" +
                "(customer_title, customer_content, user_index)\n" +
                "VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, insertQnaVo.getTitle());
            preparedStatement.setString(2, insertQnaVo.getContent());
            preparedStatement.setInt(3, insertQnaVo.getUserIndex());
            preparedStatement.execute();
        }
    }

    //    -------------------------------------------------------------------------------------------- READ (select)
    public ArrayList<QnaVo> qnaList(Connection connection, UserVo userVo, int page) throws SQLException {
        QnaVo qnaVo = null;
        ArrayList<QnaVo> qnaList = new ArrayList<>();
        String query = "" +
                "SELECT `customer_index`   AS `customerIndex`,\n" +
                "       `customer_title`   AS `customerTitle`,\n" +
                "       `customer_content` AS `customerContent`,\n" +
                "       `customer_date`    AS `customerDate`,\n" +
                "       `user_name`        AS `customerName`\n" +
                "FROM `shopping`.`customers`\n" +
                "         INNER JOIN `shopping`.users\n" +
                "                    ON `users`.`user_index` = ? \n" +
                "ORDER BY `customer_date` DESC limit ?, 10";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userVo.getUserIndex());
            preparedStatement.setInt(2, (page - 1) * 10);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    qnaVo = new QnaVo(resultSet.getInt("customerIndex"),
                            resultSet.getString("customerTitle"),
                            resultSet.getString("customerContent"),
                            resultSet.getTimestamp("customerDate"),
                            resultSet.getString("customerName"));
                    qnaList.add(qnaVo);
                }
            }
        }
        return qnaList;
    }

    public int totalQnaCount(Connection connection) throws SQLException {
        int count = 0;
        String query = "" +
                "SELECT COUNT(`customer_index`) AS `count`\n" +
                "FROM `shopping`.`customers`";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    count = resultSet.getInt("count");
                }
            }
        }
        return count;
    }
}
