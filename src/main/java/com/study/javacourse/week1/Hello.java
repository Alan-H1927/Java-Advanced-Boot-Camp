package com.study.javacourse.week1;

/**
 * 自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。
 *
 * 使用命令：
 * javac -encoding UTF-8 -g Hello.java
 * javap -c Hello.class
 *
 * 结果见图片hello.png
 *
 * 更详细
 * javap -v Hello.class
 *
 * @author me-ht
 * @date 2021-05-09
 */
public class Hello {

    private static final byte BYTE_VALUE = 1;

    private static final short SHORT_VALUE = 1;

    private static final int INT_VALUE = 1;

    private static final long LONG_VALUE = 1L;

    private static final boolean BOOLEAN_VALUE = true;

    private static final char CHAR_VALUE = 1;

    private static final float FLOAT_VALUE = 1;

    private static final double DOUBLE_VALUE = 1;

    public static void main(String[] args) {
        System.out.println(SHORT_VALUE + INT_VALUE);

        if (BOOLEAN_VALUE) {
            System.out.println(false);
        }

        for (int i = 0; i < 2; i++) {
            System.out.println(BYTE_VALUE+LONG_VALUE);
            System.out.println(CHAR_VALUE+LONG_VALUE);
            System.out.println(FLOAT_VALUE+LONG_VALUE);
            System.out.println(DOUBLE_VALUE+LONG_VALUE);
        }
    }
}
