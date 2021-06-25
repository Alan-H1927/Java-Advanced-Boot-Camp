package com.study.week7;

import com.study.id.OrderIdUtil;
import com.study.thread.CommonThreadPool;
import com.study.week5.hikari.HikariUtil;
import com.study.week5.jdbc.JdbcUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-15
 */
public class Order100WTest {

    private static final int ORDER_NUMBER = 1000000;

    private String getInsertSql() {
        return "insert\n" +
                "\tinto\n" +
                "\tjavacourse.javacourse_order (order_id,\n" +
                "\torder_status,\n" +
                "\torder_goods_count,\n" +
                "\torder_goods_price,\n" +
                "\torder_actual_pay_amount,\n" +
                "\torder_actual_pay_person,\n" +
                "\torder_actual_pay_type,\n" +
                "\torder_remark,\n" +
                "\torder_user_id,\n" +
                "\tcreate_person,\n" +
                "\tcreate_machine,\n" +
                "\tupdate_person,\n" +
                "\tupdate_machine)\n" +
                "values('" + OrderIdUtil.generateId() + "',\n" +
                "'',\n" +
                "0,\n" +
                "0,\n" +
                "0,\n" +
                "'',\n" +
                "'',\n" +
                "'',\n" +
                "'',\n" +
                "'',\n" +
                "'',\n" +
                "'',\n" +
                "'')";
    }

    /**
     * jdbc只能插入16000条数据，就会报错，连接无法新建
     */
    @Test
    public void jdbcOrderTest() {
        // 当前时间对应的毫秒数
        long startTime = System.currentTimeMillis();
        System.out.println("begin time:" + startTime);
        int sum = 0;
        try {
            for (int i = 0; i < ORDER_NUMBER; i++) {
                JdbcUtil.executeUpdate(getInsertSql(), null);
                sum++;
            }
        } catch (Exception e) {
            System.out.println("报错插入行数为：" + sum);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("end time:" + endTime);
        long finalTime = (endTime - startTime) / 1000;
        System.out.println("总耗时为:" + finalTime + "秒");
    }

    /**
     * jdbc+线程池
     * <p>
     * 19500报错连接无法新建
     */
    @Test
    public void jdbcOrderMultiThreadTest() {
        // 当前时间对应的毫秒数
        long startTime = System.currentTimeMillis();
        System.out.println("begin time:" + startTime);
        int sum = 0;
        CountDownLatch countDownLatch = new CountDownLatch(ORDER_NUMBER);
        try {
            for (int i = 0; i < ORDER_NUMBER; i++) {
                CommonThreadPool.execute(() -> {
                    JdbcUtil.executeUpdate(getInsertSql(), null);
                    countDownLatch.countDown();
                    System.out.println("for循环插入数量为：" + (ORDER_NUMBER - countDownLatch.getCount()));
                });
                sum++;
            }
        } catch (Exception e) {
            System.out.println("报错插入行数为：" + sum);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("end time:" + endTime);
        long finalTime = (endTime - startTime) / 1000;
        System.out.println("总耗时为:" + finalTime + "秒");
    }

    /**
     * jdbc批量插入
     * <p>
     * 空表：155秒
     *
     * 开启rewriteBatchedStatements=true：21秒
     */
    @Test
    public void jdbcBatchOrderTest() {
        // 当前时间对应的毫秒数
        long startTime = System.currentTimeMillis();
        System.out.println("begin time:" + startTime);
        int sum = 0;
        try {
            for (int i = 0; i < 10; i++) {
                JdbcUtil.executeBatchUpdate(getInsertSql(), null, 100000);
                sum++;
                System.out.println("jdbc循环插入" + sum);
            }
        } catch (Exception e) {
            System.out.println("报错插入行数为：" + sum);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("end time:" + endTime);
        long finalTime = (endTime - startTime) / 1000;
        System.out.println("总耗时为:" + finalTime + "秒");
    }

    /**
     * 使用Hikari连接池的效率比JDBC高，而且不会报JDBC的错误，可以持续插入
     *
     * 速度很慢
     *
     * <p>
     * 空表：1980秒
     *
     * 开启rewriteBatchedStatements=true：434秒
     */
    @Test
    public void hikariOrderTest() {
        // 当前时间对应的毫秒数
        long startTime = System.currentTimeMillis();
        System.out.println("begin time:" + startTime);
        int sum = 0;
        try {
            for (int i = 0; i < ORDER_NUMBER; i++) {
                HikariUtil.executeUpdate(getInsertSql(), null);
                sum++;
                System.out.println("for循环插入数量为：" + sum);
            }
        } catch (Exception e) {
            System.out.println("报错插入行数为：" + sum);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("end time:" + endTime);
        long finalTime = (endTime - startTime) / 1000;
        System.out.println("总耗时为:" + finalTime + "秒");
    }

    /**
     * 使用线程池和连接池
     * <p>
     * 使用线程池：因为线程池处理速度没有那么快，都在队列积压，因为是异步，所以主线程会退出，导致无法完成100W的插入
     * 必须等待队列要设置无限长度，必须要锁住主线程，等待其他线程运行完成才可以完成100W的插入
     * <p>
     * <p>
     * 空表：432秒
     *
     * 开启rewriteBatchedStatements=true：
     */
    @Test
    public void hikariOrderTestAndMultiThreadTest() {
        // 当前时间对应的毫秒数
        long startTime = System.currentTimeMillis();
        System.out.println("begin time:" + startTime);
        int sum = 0;
        CountDownLatch countDownLatch = new CountDownLatch(ORDER_NUMBER);
        try {
            for (int i = 0; i < ORDER_NUMBER; i++) {
                CommonThreadPool.execute(() -> {
                    HikariUtil.executeUpdate(getInsertSql(), null);
                    countDownLatch.countDown();
                    System.out.println("for循环插入数量为：" + (ORDER_NUMBER - countDownLatch.getCount()));
                });
                sum++;
            }
        } catch (Exception e) {
            System.out.println("报错插入行数为：" + sum);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("end time:" + endTime);
        long finalTime = (endTime - startTime) / 1000;
        System.out.println("总耗时为:" + finalTime + "秒");
    }

    /**
     * hikari连接池+批量插入，
     * <p>
     * 空表：163秒
     *
     * 开启rewriteBatchedStatements=true：34秒
     */
    @Test
    public void hikariBatchOrderTest() {
        // 当前时间对应的毫秒数
        long startTime = System.currentTimeMillis();
        System.out.println("begin time:" + startTime);
        try {
            HikariUtil.executeBatchUpdate(getInsertSql(), null, ORDER_NUMBER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("end time:" + endTime);
        long finalTime = (endTime - startTime) / 1000;
        System.out.println("总耗时为:" + finalTime + "秒");
    }

    /**
     * 使用线程池+连接池+批量插入
     * <p>
     * 查询时出现明显的等待
     * <p>
     * 空表：51秒
     *
     * 开启rewriteBatchedStatements=true：11秒
     */
    @Test
    public void hikariBatchOrderTestAndMultiThreadTest() {
        // 当前时间对应的毫秒数
        long startTime = System.currentTimeMillis();
        System.out.println("begin time:" + startTime);
        CountDownLatch countDownLatch = new CountDownLatch(CommonThreadPool.get().getCorePoolSize());
        try {
            int sum = ORDER_NUMBER / CommonThreadPool.get().getCorePoolSize() + 1;
            System.out.println("每个线程池，批量插入：" + sum);
            for (int i = 0; i < CommonThreadPool.get().getCorePoolSize(); i++) {
                CommonThreadPool.execute(() -> {
                    try {
                        HikariUtil.executeBatchUpdate(getInsertSql(), null, sum);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    countDownLatch.countDown();
                    System.out.println("线程池插入数量为：" + (CommonThreadPool.get().getCorePoolSize() - countDownLatch.getCount()));
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("end time:" + endTime);
        long finalTime = (endTime - startTime) / 1000;
        System.out.println("总耗时为:" + finalTime + "秒");
    }
}
