package com.tieria.shopping.apis.user.daos;

import com.tieria.shopping.apis.user.vos.UserLoginVo;
import com.tieria.shopping.apis.user.vos.UserSignUpVo;
import com.tieria.shopping.common.UserVo;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    //    -------------------------------------------------------------------------------------------- CREATE (insert)
    public void insertUser(Connection connection, UserSignUpVo userSignUpVo) throws SQLException{
        UserVo userVo = null;
        String query = "" +
                "INSERT INTO `shopping`.`users`\n" +
                "(`user_name`,\n" +
                " `user_email`,\n" +
                " `user_password`,\n" +
                " `user_level`)\n" +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userSignUpVo.getUserName());
            preparedStatement.setString(2, userSignUpVo.getUserEmail());
            preparedStatement.setString(3, userSignUpVo.getHashPassword());
            preparedStatement.setInt(4, userSignUpVo.getUserLevel());
            preparedStatement.execute();
        }
    }

    public int countName(Connection connection, String userName) throws SQLException {
        int count;
        String query= "" +
                "SELECT COUNT('user_index') AS 'count'\n" +
                "FROM `shopping`.`users`\n" +
                "WHERE `user_name` = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userName);
            preparedStatement.executeQuery();
            try(ResultSet resultSet = preparedStatement.getResultSet()){
                resultSet.next();
                count = resultSet.getInt("count");
            }
        }
        return count;
    }

    public int countEmail(Connection connection, String userEmail) throws SQLException {
        int count;
        String query= "" +
                "SELECT COUNT('user_index') AS 'count'\n" +
                "FROM `shopping`.`users`\n" +
                "WHERE `user_email` = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userEmail);
            preparedStatement.executeQuery();
            try(ResultSet resultSet = preparedStatement.getResultSet()){
                resultSet.next();
                count = resultSet.getInt("count");
            }
        }
        return count;
    }
    //    -------------------------------------------------------------------------------------------- READ (select)
    public UserVo selectUser(Connection connection, UserLoginVo userLoginVo) throws SQLException {
        UserVo userVo = null;
        String query = "" +
                "SELECT `user_index`    AS `userIndex`,\n" +
                "       `user_name`     AS `userName`,\n" +
                "       `user_email`    AS `userEmail`,\n" +
                "       `user_password` AS `userPassword`,\n" +
                "       `user_level` AS `userLevel`\n" +
                "FROM `shopping`.`users`\n" +
                "WHERE `user_name` = ?\n" +
                "  AND `user_password` = ?\n" +
                "LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, userLoginVo.getUserName());
            preparedStatement.setString(2, userLoginVo.getHashedPassword());
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()){
                while (resultSet.next()) {
                    userVo = new UserVo(
                            resultSet.getInt("userIndex"),
                            resultSet.getString("userName"),
                            resultSet.getString("userEmail"),
                            resultSet.getString("userPassword"),
                            resultSet.getInt("userLevel")
                    );
                }
            }
        }
        return userVo;
    }
}
