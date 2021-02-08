package com.tieria.shopping.apis.customer.services;

import com.tieria.shopping.apis.customer.containers.CustomerResultContainer;
import com.tieria.shopping.apis.customer.daos.CustomerDao;
import com.tieria.shopping.apis.customer.enums.CustomerResult;
import com.tieria.shopping.apis.customer.vos.InsertQnaVo;
import com.tieria.shopping.apis.customer.vos.QnaVo;
import com.tieria.shopping.common.UserVo;
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

    //    -------------------------------------------------------------------------------------------- READ (select)
    public CustomerResultContainer getQnaList(UserVo userVo, int page) throws SQLException {
        if (userVo == null) {
            return new CustomerResultContainer(CustomerResult.NOT_ALLOWED, null);
        }
        try(Connection connection = this.dataSource.getConnection()) {
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

    public int getTotalQnaCount() throws SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            return this.customerDao.totalQnaCount(connection);
        }
    }
}
