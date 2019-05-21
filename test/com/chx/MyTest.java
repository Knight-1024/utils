package com.chx;

import org.junit.*;

/**
 * 简单单元测试实例
 * Created by CHX on 2019/5/21.
 */
public class MyTest {

    @Before
    public void init(){
        System.out.println("@Test运行之前执行。");
    }

    @Test
    public void myTest(){
        System.out.println("this is test.");
    }

    @Test
    public void myTest2(){
        System.out.println("this is test too.");
    }

    @After
    public void end(){
        System.out.println("@Test运行之后执行。");
    }

}
