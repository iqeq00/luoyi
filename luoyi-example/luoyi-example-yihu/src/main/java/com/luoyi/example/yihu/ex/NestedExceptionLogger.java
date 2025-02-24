package com.luoyi.example.yihu.ex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NestedExceptionLogger {
    private static final Logger log = LoggerFactory.getLogger(NestedExceptionLogger.class);

    public static void main(String[] args) {
        try {
            // 模拟嵌套异常
            throwNestedExceptions(6);
        } catch (Exception e) {
            logException(e, 5); // 调用自定义的日志记录方法，只记录前5个异常
            e.printStackTrace();
        }
    }

    // 模拟抛出嵌套异常
    private static void throwNestedExceptions(int depth) throws Exception {
        if (depth <= 0) {
            throw new Exception("Base Exception");
        } else {
            try {
                throwNestedExceptions(depth - 1);
            } catch (Exception e) {
                throw new Exception("Exception at depth " + depth, e);
            }
        }
    }


    public static void logException(Exception e, int length) {
        // 获取异常的堆栈跟踪信息
        StackTraceElement[] stackTraceElements = e.getStackTrace();

        // 创建一个 StringBuilder 来构建日志信息
        StringBuilder sb = new StringBuilder();
        sb.append("异常错误信息（Exception）：").append(e.getMessage()).append("\n");

        // 只记录前五个堆栈信息
        int limit = Math.min(stackTraceElements.length, length);
        for (int i = 0; i < limit; i++) {
            sb.append("\t").append(stackTraceElements[i].toString()).append("\n");
        }

        // 记录日志
        log.info(sb.toString());
    }

    // 记录异常及其堆栈信息，最多记录5个异常
    private static void logException1(Exception e, int limit) {
        StringBuilder sb = new StringBuilder();
        sb.append("异常错误信息（Exception）：").append(e.getMessage()).append("\n");

        // 记录当前异常的堆栈信息
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        int stackLimit = Math.min(stackTraceElements.length, 5);
        for (int i = 0; i < stackLimit; i++) {
            sb.append("\t").append(stackTraceElements[i].toString()).append("\n");
        }

        // 递归记录嵌套异常
        int count = 1;
        while (e.getCause() != null && count < limit) {
            e = (Exception) e.getCause();
            sb.append("嵌套异常 ").append(count + 1).append("：").append(e.getMessage()).append("\n");
            stackTraceElements = e.getStackTrace();
            stackLimit = Math.min(stackTraceElements.length, 5);
            for (int i = 0; i < stackLimit; i++) {
                sb.append("\t").append(stackTraceElements[i].toString()).append("\n");
            }
            count++;
        }

        // 记录日志
        log.info(sb.toString());
    }
}

