package com.luoyi.example.jd.util;

public class ExTest {


    public static void main(String[] args) {
        try {
            test1();
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                System.out.println("运行时"+e.getMessage());
            } else {
                System.out.println("父类"+e.getMessage());
            }
        }
    }

    private static void test1() throws Exception {

        throw new Exception("111");
    }
}
