package com.tieria.shopping.apis.customer.services;

import com.tieria.shopping.apis.customer.containers.*;
import com.tieria.shopping.apis.customer.daos.CustomerDao;
import com.tieria.shopping.apis.customer.enums.CustomerResult;
import com.tieria.shopping.apis.customer.vos.*;
import com.tieria.shopping.common.UserVo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class CustomerService {
    private final DataSource dataSource;
    private final CustomerDao customerDao;

    @Autowired
    public CustomerService(DataSource dataSource, CustomerDao customerDao) {
        this.dataSource = dataSource;
        this.customerDao = customerDao;
    }

    //    -------------------------------------------------------------------------------------------- CREATE (insert)
    public CustomerResult insertQna(UserVo userVo, InsertQnaVo insertQnaVo) throws SQLException {
        if (userVo.getUserIndex() != insertQnaVo.getUserIndex()) {
            return CustomerResult.NOT_ALLOWED;
        }
        try (Connection connection = this.dataSource.getConnection()) {
            if (insertQnaVo.getTitle().isEmpty() || insertQnaVo.getContent().isEmpty()) {
                return CustomerResult.FAILURE;
            }
            this.customerDao.insertQna(connection, insertQnaVo);
            return CustomerResult.SUCCESS;
        }
    }

    public CustomerResult insertAnswer(UserVo userVo, InsertAnswerVo insertAnswerVo, int index) throws SQLException {
        if (userVo.getUserIndex() != insertAnswerVo.getUserIndex()) {
            return CustomerResult.NOT_ALLOWED;
        }
        try (Connection connection = this.dataSource.getConnection()) {
            if (insertAnswerVo.getAnsContent().isEmpty()) {
                return CustomerResult.FAILURE;
            }
            this.customerDao.insertAnswer(connection, insertAnswerVo, index);
            return CustomerResult.SUCCESS;
        }
    }

    //    -------------------------------------------------------------------------------------------- READ (select)
    public CustomerResultContainer getQnaList(UserVo userVo, int page) throws SQLException {
        if (userVo == null) {
            return new CustomerResultContainer(CustomerResult.NOT_ALLOWED, null);
        }
        try (Connection connection = this.dataSource.getConnection()) {
            ArrayList<QnaVo> qnaResultList = new ArrayList<>();
            ArrayList<QnaVo> qnaDaoList = this.customerDao.qnaList(connection, userVo, page);
            if (qnaDaoList == null) {
                return new CustomerResultContainer(CustomerResult.FAILURE, null);
            } else {
                for (QnaVo qna : qnaDaoList) {
                    qnaResultList.add(new QnaVo(
                            qna.getCustomerIndex(),
                            qna.getCustomerTitle(),
                            qna.getCustomerContent(),
                            qna.getCustomerDate(),
                            qna.getCustomerName()
                    ));
                }
                return new CustomerResultContainer(CustomerResult.SUCCESS, qnaResultList);
            }
        }
    }

    public int getTotalQnaCount(UserVo userVo) throws SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            return this.customerDao.totalQnaCount(connection, userVo);
        }
    }

    public QuestionResultContainer getQna(UserVo userVo, int index) throws SQLException {
        if (userVo == null) {
            return new QuestionResultContainer(CustomerResult.NOT_ALLOWED, null);
        }
        try (Connection connection = this.dataSource.getConnection()) {
            QnaVo qnaVo = null;
            QnaVo question = this.customerDao.getQna(connection, index);
            if (question == null) {
                return new QuestionResultContainer(CustomerResult.FAILURE, null);
            } else {
                qnaVo = new QnaVo(question.getCustomerIndex(),
                        question.getCustomerTitle(),
                        question.getCustomerContent(),
                        question.getCustomerDate(),
                        question.getCustomerName());
                return new QuestionResultContainer(CustomerResult.SUCCESS, qnaVo);
            }
        }
    }

    public AnsResultContainer getAns(UserVo userVo, int index) throws SQLException {
        if (userVo == null || userVo.getUserLevel() != 1) {
            return new AnsResultContainer(CustomerResult.NOT_ALLOWED, null);
        }
        try (Connection connection = this.dataSource.getConnection()) {
            AnswerVo answerVo = null;
            AnswerVo answer = this.customerDao.getAnswer(connection, index);
            if (answer == null) {
                return new AnsResultContainer(CustomerResult.FAILURE, null);
            } else {
                answerVo = new AnswerVo(
                        answer.getAnsIndex(),
                        answer.getAnsContent(),
                        answer.getAnsDate(),
                        answer.getUserName(),
                        answer.getCustomerIndex());
                return new AnsResultContainer(CustomerResult.SUCCESS, answerVo);
            }
        }
    }

    public QnaAllListResultContainer getQnaAllList(UserVo userVo, int page) throws SQLException {
        if (userVo == null || userVo.getUserLevel() != 1) {
            return new QnaAllListResultContainer(CustomerResult.NOT_ALLOWED, null);
        }
        try (Connection connection = this.dataSource.getConnection()) {
            ArrayList<QnaAllVo> qnaResultAllList = new ArrayList<>();
            ArrayList<QnaAllVo> qnaDaoAllList = this.customerDao.qnaAllList(connection, page);
            if (qnaDaoAllList == null) {
                return new QnaAllListResultContainer(CustomerResult.FAILURE, null);
            } else {
                for (QnaAllVo qna : qnaDaoAllList) {
                    qnaResultAllList.add(new QnaAllVo(
                            qna.getQnaIndex(),
                            qna.getQnaTitle(),
                            qna.getQnaContent(),
                            qna.getQnaDate(),
                            qna.getUserName(),
                            qna.getAnsIndex()
                    ));
                }
                return new QnaAllListResultContainer(CustomerResult.SUCCESS, qnaResultAllList);
            }
        }
    }

    public int getTotalQnaAllCount() throws SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            return this.customerDao.totalQnaAllCount(connection);
        }
    }

    public AnsListResultContainer getAnsList(UserVo userVo, int page) throws SQLException {
        if (userVo == null || userVo.getUserLevel() != 1) {
            return new AnsListResultContainer(CustomerResult.NOT_ALLOWED, null);
        }
        try (Connection connection = this.dataSource.getConnection()) {
            ArrayList<AnswerListVo> ansResultList = new ArrayList<>();
            ArrayList<AnswerListVo> ansDaoList = this.customerDao.ansList(connection, page);
            if (ansDaoList == null) {
                return new AnsListResultContainer(CustomerResult.FAILURE, null);
            } else {
                for (AnswerListVo ans : ansDaoList) {
                    ansResultList.add(new AnswerListVo(
                            ans.getQnaIndex(),
                            ans.getAnsContent(),
                            ans.getUserName(),
                            ans.getAsnDate()
                    ));
                }
                return new AnsListResultContainer(CustomerResult.SUCCESS, ansResultList);
            }
        }
    }

    public int getTotalAnsCount() throws SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            return this.customerDao.totalAnsCount(connection);
        }
    }

    //    -------------------------------------------------------------------------------------------- UPDATE
    public CustomerResult updateQna(UserVo userVo, UpdateQnaVo updateQnaVo, int index) throws SQLException {
        if (userVo == null) {
            return CustomerResult.NOT_ALLOWED;
        }
        try (Connection connection = this.dataSource.getConnection()) {
            if (this.customerDao.updateQna(connection, updateQnaVo, index) != 1) {
                return CustomerResult.FAILURE;
            } else {
                return CustomerResult.SUCCESS;
            }
        }
    }

    public CustomerResult updateAns(UserVo userVo, UpdateAnsVo updateAnsVo) throws SQLException {
        if (userVo == null) {
            return CustomerResult.NOT_ALLOWED;
        }
        try (Connection connection = this.dataSource.getConnection()) {
            if (this.customerDao.updateAns(connection, updateAnsVo) != 1) {
                return CustomerResult.FAILURE;
            } else {
                return CustomerResult.SUCCESS;
            }
        }
    }

    //    -------------------------------------------------------------------------------------------- DELETE
    public CustomerResult deleteQna(UserVo userVo, int index) throws SQLException {
        if (userVo == null) {
            return CustomerResult.NOT_ALLOWED;
        }
        try (Connection connection = this.dataSource.getConnection()) {
            if (this.customerDao.deleteQna(connection, index) != 1) {
                return CustomerResult.FAILURE;
            } else {
                return CustomerResult.SUCCESS;
            }
        }
    }

    public CustomerResult deleteAns(UserVo userVo, int index) throws SQLException {
        if (userVo == null) {
            return CustomerResult.NOT_ALLOWED;
        }
        try (Connection connection = this.dataSource.getConnection()) {
            if (this.customerDao.deleteAns(connection, index) != 1 || index <= 0) {
                return CustomerResult.FAILURE;
            } else {
                return CustomerResult.SUCCESS;
            }
        }
    }
}
