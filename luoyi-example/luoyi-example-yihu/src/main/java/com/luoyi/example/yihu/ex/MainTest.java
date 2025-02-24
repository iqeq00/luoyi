package com.luoyi.example.yihu.ex;

public class MainTest {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        String s = null;
        int max = 100;
        for (int i = 0; i <= max; i++) {
            try {
                s.toString();
            } catch (NullPointerException e) {
//                if (i == 0 || i == max) {
                System.out.println("============================");
                System.out.println("第"+ i +"次堆栈信息");
                /**
                 * jvm参数增加-XX:-OmitStackTraceInFastThrow
                 * 否则只输出java.lang.NullPointerException，没有堆栈信息
                 */
                e.printStackTrace();
//                }
            }
        }
    }

}
