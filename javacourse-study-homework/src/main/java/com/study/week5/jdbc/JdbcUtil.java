package com.study.week5.jdbc;

import com.study.utils.properties.ResourceUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-05
 */
public class JdbcUtil {
    private JdbcUtil() {
    }

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

    static {
        try {
            Class.forName(DRIVER_PATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("加载驱动错误！");
        }
    }

    /**
     * 获取连接
     *
     * @author huangtao
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
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
     * @param sql
     * @param params
     * @return
     */
    public static List<Map<String, Object>> executeQueryForMap(String sql, Object[] params) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
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
            //获取结果集属性信息
            ResultSetMetaData md = rs.getMetaData();
            //获取结果集的列数
            int col = md.getColumnCount();
            //遍历结果集
            while (rs.next()) {
                Map<String, Object> dataMap = new HashMap<>();
                for (int i = 1; i <= col; i++) {
                    //获取指定位置的字段名称
                    String key = md.getColumnName(i);
                    //获取指定位置的字段值
                    Object value = rs.getObject(i);
                    dataMap.put(key, value);
                    list.add(dataMap);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }
        return list;
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

}
