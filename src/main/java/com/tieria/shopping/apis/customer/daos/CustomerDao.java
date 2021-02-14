package com.tieria.shopping.apis.customer.daos;

import com.tieria.shopping.apis.customer.vos.*;
import com.tieria.shopping.common.UserVo;
import org.apache.catalina.User;
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

    public void insertAnswer(Connection connection, InsertAnswerVo insertAnswerVo, int index) throws SQLException {
        String query = "" +
                "INSERT INTO `shopping`.`answers`\n" +
                "    (answer_content, user_index, customer_index)\n" +
                "VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, insertAnswerVo.getAnsContent());
            preparedStatement.setInt(2, insertAnswerVo.getUserIndex());
            preparedStatement.setInt(3, index);
            preparedStatement.execute();
        }
    }

    //    -------------------------------------------------------------------------------------------- READ (select)
    // QNA LIST
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
                "                    ON `users`.`user_index` = `customers`.`user_index` \n" +
                "WHERE `customers`.`user_index` = ? \n" +
                "ORDER BY `customer_index` DESC limit ?, 10";
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

    // QNA COUNT
    public int totalQnaCount(Connection connection, UserVo userVo) throws SQLException {
        int count = 0;
        String query = "" +
                "SELECT COUNT(`customer_index`) AS `count`\n" +
                "FROM `shopping`.`customers`\n" +
                "         INNER JOIN `shopping`.users\n" +
                "             ON `customers`.`user_index` = `users`.`user_index`\n" +
                "WHERE `users`.`user_index` = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userVo.getUserIndex());
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    count = resultSet.getInt("count");
                }
            }
        }
        return count;
    }

    // QNA 1 SELECT
    public QnaVo getQna(Connection connection, int index) throws SQLException {
        QnaVo qnaVo = null;
        String query = "" +
                "SELECT `customer_index`    AS `customerIndex`,\n" +
                "       `customer_title`    AS `customerTitle`,\n" +
                "       `customer_content`  AS `customerContent`,\n" +
                "       `customer_date`     AS `customerDate`,\n" +
                "       `users`.`user_name` AS `userName`\n" +
                "FROM `shopping`.`customers`\n" +
                "         INNER JOIN `shopping`.users\n" +
                "                    ON `users`.`user_index` = `customers`.`user_index`\n" +
                "WHERE `customer_index` = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, index);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    qnaVo = new QnaVo(
                            resultSet.getInt("customerIndex"),
                            resultSet.getString("customerTitle"),
                            resultSet.getString("customerContent"),
                            resultSet.getTimestamp("customerDate"),
                            resultSet.getString("userName")
                    );
                }
            }
        }
        return qnaVo;
    }

    // ANSWER 1 SELECT
    public AnswerVo getAnswer(Connection connection, int index) throws SQLException {
        AnswerVo ansVo = null;
        String query = "" +
                "SELECT `answer_index`   AS `ansIndex`,\n" +
                "       `answer_content` AS `ansContent`,\n" +
                "       `answer_date`    AS `ansDate`,\n" +
                "       `user_name`      AS `userName`,\n" +
                "       `customer_index` AS `customerIndex`\n" +
                "FROM `shopping`.`answers`\n" +
                "         INNER JOIN `shopping`.`users`\n" +
                "                    ON `answers`.`user_index` = `users`.`user_index`\n" +
                "WHERE `customer_index` = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, index);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ansVo = new AnswerVo(
                            resultSet.getInt("ansIndex"),
                            resultSet.getString("ansContent"),
                            resultSet.getTimestamp("ansDate"),
                            resultSet.getString("userName"),
                            resultSet.getInt("customerIndex")
                    );
                }
            }
        }
        return ansVo;
    }

    // QNA ALL LIST
    public ArrayList<QnaAllVo> qnaAllList(Connection connection, int page) throws SQLException {
        QnaAllVo qnaAllVo = null;
        ArrayList<QnaAllVo> qnaAllList = new ArrayList<>();
        String query = "" +
                "SELECT `customers`.`customer_index` AS `qnaIndex`,\n" +
                "       `customer_title`             AS `qnaTitle`,\n" +
                "       `customer_content`           AS `qnaContent`,\n" +
                "       `customer_date`              AS `qnaDate`,\n" +
                "       `user_name`                  AS `userName`,\n" +
                "       `answer_index`               AS `ansIndex`\n" +
                "FROM `shopping`.`customers`\n" +
                "         LEFT JOIN `shopping`.`users`\n" +
                "                   ON `customers`.`user_index` = `users`.`user_index`\n" +
                "         LEFT JOIN `shopping`.`answers`\n" +
                "                   ON `customers`.`customer_index` = `answers`.`customer_index`\n" +
                "ORDER BY customers.customer_index DESC\n" +
                "LIMIT ?, 10;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, (page - 1) * 10);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    qnaAllVo = new QnaAllVo(
                            resultSet.getInt("qnaIndex"),
                            resultSet.getString("qnaTitle"),
                            resultSet.getString("qnaContent"),
                            resultSet.getTimestamp("qnaDate"),
                            resultSet.getString("userName"),
                            resultSet.getInt("ansIndex")
                    );
                    qnaAllList.add(qnaAllVo);
                }
            }
        }
        return qnaAllList;
    }

    // QNA ALL COUNT
    public int totalQnaAllCount(Connection connection) throws SQLException {
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

    // QNA ALL LIST
    public ArrayList<AnswerListVo> ansList(Connection connection, int page) throws SQLException {
        AnswerListVo answerListVo = null;
        ArrayList<AnswerListVo> ansList = new ArrayList<>();
        String query = "" +
                "SELECT `answers`.`customer_index` AS `qnaIndex`,\n" +
                "       `answer_content`           AS `ansContent`,\n" +
                "       `user_name`                AS `userName`,\n" +
                "       `answer_date`              AS `ansDate`\n" +
                "FROM `shopping`.`answers`\n" +
                "         INNER JOIN `shopping`.`customers`\n" +
                "                    ON `answers`.`customer_index` = `customers`.`customer_index`\n" +
                "         INNER JOIN `shopping`.`users`\n" +
                "                    ON `customers`.`user_index` = `users`.`user_index`\n" +
                "ORDER BY `answers`.`customer_index` DESC\n" +
                "LIMIT ?, 10";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, (page - 1) * 10);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    answerListVo = new AnswerListVo(
                            resultSet.getInt("qnaIndex"),
                            resultSet.getString("ansContent"),
                            resultSet.getString("userName"),
                            resultSet.getTimestamp("ansDate")
                    );
                    ansList.add(answerListVo);
                }
            }
        }
        return ansList;
    }

    // QNA ALL COUNT
    public int totalAnsCount(Connection connection) throws SQLException {
        int count = 0;
        String query = "" +
                "SELECT COUNT(`answer_index`) AS `count`\n" +
                "FROM `shopping`.`answers`";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    count = resultSet.getInt("count");
                }
            }
        }
        return count;
    }

    //    -------------------------------------------------------------------------------------------- UPDATE
    public int updateQna(Connection connection, UpdateQnaVo updateQnaVo, int index) throws SQLException {
        int result = 0;
        String query = "" +
                "UPDATE `shopping`.`customers`\n" +
                "SET `customer_title`   = ?,\n" +
                "    `customer_content` = ?,\n" +
                "    `customer_date`    = now()\n" +
                "WHERE `customer_index` = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, updateQnaVo.getQnaTitle());
            preparedStatement.setString(2, updateQnaVo.getQnaContent());
            preparedStatement.setInt(3, index);
            preparedStatement.executeUpdate();
            result = 1;
        }
        return result;
    }

    public int updateAns(Connection connection, UpdateAnsVo updateAnsVo) throws SQLException {
        int result = 0;
        String query = "" +
                "UPDATE `shopping`.`answers`\n" +
                "SET `answer_content` = ?,\n" +
                "    `answer_date`    = now()\n" +
                "WHERE `customer_index` = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, updateAnsVo.getContent());
            preparedStatement.setInt(2, updateAnsVo.getIndex());
            preparedStatement.executeUpdate();
            result = 1;
        }
        return result;
    }

    //    -------------------------------------------------------------------------------------------- DELETE
    public int deleteQna(Connection connection, int index) throws SQLException {
        int deleteResult = 0;
        String query = "" +
                "DELETE\n" +
                "FROM `shopping`.`customers`\n" +
                "WHERE `customer_index` = ?\n" +
                "LIMIT 1";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, index);
            deleteResult = preparedStatement.executeUpdate();
        }
        return deleteResult;
    }

    public int deleteAns(Connection connection, int index) throws SQLException {
        int deleteResult = 0;
        String query = "" +
                "DELETE\n" +
                "FROM `shopping`.`answers`\n" +
                "WHERE `customer_index` = ?\n" +
                "LIMIT 1";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, index);
            deleteResult = preparedStatement.executeUpdate();
        }
        return deleteResult;
    }
}
