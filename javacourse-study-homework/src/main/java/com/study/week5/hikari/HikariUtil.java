package com.study.week5.hikari;

import com.study.properties.ResourceUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-05
 */
public class HikariUtil {

    /**
     * 驱动类完整路径
     */
    private static final String DRIVER_PATH = ResourceUtil.getConf("jdbc.drive.path");
    /**
     * 连接URL
     */
    private static final String URL = ResourceUtil.getConf("jdbc.url");
    /**
     * 用户名
     */
    private static final String USERNAME = ResourceUtil.getConf("jdbc.username");
    /**
     * 密码
     */
    private static final String PASSWORD = ResourceUtil.getConf("jdbc.password");

    private HikariUtil() {
    }

    /**
     * 定义HikariDataSource类型的dataSource
     */
    private static HikariDataSource dataSource = null;

    static {
        HikariConfig config = new HikariConfig();
        // 将config对象传入给HikariDataSource ，返回dataSource
        config.setDriverClassName(DRIVER_PATH);
        config.setJdbcUrl(URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        dataSource = new HikariDataSource(config);
    }

    /**
     * 通过数据源获取连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 执行DQL语句，返回List数据
     *
     * @param sql
     * @param params
     * @author huangtao
     */
    public static List<Object[]> executeQuery(String sql, Object[] params) {
        List<Object[]> list = new ArrayList<Object[]>();
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            //执行sql语句，获取结果集
            rs = ps.executeQuery();
            //获取结果集的列数
            int col = rs.getMetaData().getColumnCount();
            //遍历结果集
            while (rs.next()) {
                Object[] obj = new Object[col];
                for (int i = 0; i < col; i++) {
                    obj[i] = rs.getObject(i + 1);
                }
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }
        return list;
    }

    /**
     * 执行DML语句，insert into、update、delete
     *
     * @param sql
     * @param params
     * @author huangtao
     */
    public static int executeUpdate(String sql, Object[] params) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        int row = 0;
        try {
            ps = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            row = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
        return row;
    }

    /**
     * 关闭
     *
     * @param conn
     * @param sta
     * @author huangtao
     */
    private static void close(Connection conn, Statement sta, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (sta != null) {
                sta.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("释放资源出错！");
        }
    }

    /**
     * 查询总数
     *
     * @param sql
     * @param params
     * @return
     */
    public static int executeCount(String sql, Object[] params) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            ps = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    //字段的索引位置从1开始，所以要加1
                    ps.setObject(i + 1, params[i]);
                }
            }
            //执行sql语句，获取结果集
            rs = ps.executeQuery();
            //遍历结果集
            while (rs.next()) {
                count = Integer.parseInt(rs.getObject(1).toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }
        return count;
    }

    /**
     * 批量
     *
     * @param sql
     * @param params
     * @param count
     * @return
     * @throws SQLException
     */
    public static int executeBatchUpdate(String sql, Object[] params, int count) throws SQLException {
        Connection conn = getConnection();
        conn.setAutoCommit(false);
        PreparedStatement ps = conn.prepareStatement(sql);
        int row = 0;
        try {
            for (int i = 0; i < count; i++) {
                try {
                    if (params != null) {
                        for (int j = 0; j < params.length; j++) {
                            ps.setObject(j + 1, params[j]);
                        }
                    }
                    ps.addBatch();
                    if (i % 2000 == 0) {
                        ps.executeBatch();
                        conn.commit();
                        ps.clearBatch();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 剩余数量不足2000
            ps.executeBatch();
            conn.commit();
            ps.clearBatch();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
        return row;
    }
}
