package com.study.week6.lambda;

import com.study.AbstractTest;
import org.junit.jupiter.api.Test;

/**
 * lambda表达式不适合写业务代码
 *
 * @author me-ht
 * @date 2021-06-08
 */
public class TestLambda extends AbstractTest {

    @Test
    public void test1() {
        String name = "lambda";
        LambdaInterface lambdaInterface = (l) -> l;
        logger.info("test1 [{}]", lambdaInterface.study(name));
    }

    @Test
    public void test2() {
        String name = "lambda";
        //逻辑直接写在这里
        LambdaInterface lambdaInterface = (l) -> l;
        InterfaceNeedLambda interfaceNeedLambda = (l) -> name + " interfaceNeedLambda";
        logger.info("test2 [{}]", interfaceNeedLambda.study(lambdaInterface));
    }
}
