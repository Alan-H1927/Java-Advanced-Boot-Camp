package com.study.service;

import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-27
 */
@Service
public class XAUserService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public XAUserService(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL = "insert\n" +
            "\tinto\n" +
            "\tjavacourse_user (user_id,\n" +
            "\tuser_username,\n" +
            "\tuser_password,\n" +
            "\tuser_email,\n" +
            "\tuser_phone_number,\n" +
            "\tuser_status,\n" +
            "\tcreate_person,\n" +
            "\tupdate_person)\n" +
            "values (?,\n" +
            "?,\n" +
            "?,\n" +
            "?,\n" +
            "?,\n" +
            "?,\n" +
            "?,\n" +
            "?)";

    /**
     * Execute XA.
     *
     * @param count insert record count
     * @return transaction type
     */
    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public TransactionType insert(final int count) {
        return jdbcTemplate.execute(SQL, (PreparedStatementCallback<TransactionType>) preparedStatement -> {
            doInsert(count, preparedStatement);
            return TransactionTypeHolder.get();
        });
    }

    /**
     * Execute XA with exception.
     *
     * @param count insert record count
     */
    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public void insertFailed(final int count) {
        jdbcTemplate.execute(SQL, (PreparedStatementCallback<TransactionType>) preparedStatement -> {
            doInsert(count, preparedStatement);
            throw new SQLException("mock transaction failed");
        });
    }

    private void doInsert(final int count, final PreparedStatement preparedStatement) throws SQLException {
        for (int i = 0; i < count; i++) {
            preparedStatement.setObject(1, String.valueOf(i));
            preparedStatement.setObject(2, "u_username");
            preparedStatement.setObject(3, "u_password");
            preparedStatement.setObject(4, "u_username@126.com");
            preparedStatement.setObject(5, "1234567");
            preparedStatement.setObject(6, "N");
            preparedStatement.setObject(7, "sys");
            preparedStatement.setObject(8, "sys");
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Select all.
     *
     * @return record count
     */
    public int selectAll() {
        return jdbcTemplate.queryForObject("SELECT COUNT(1) AS count FROM javacourse_user", Integer.class);
    }
}
