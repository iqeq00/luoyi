package com.luoyi.framework.log.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 方法执行时间追踪注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimeTrack {

    /**
     * 业务描述信息
     */
    String value() default "";

    /**
     * 时间单位，默认为毫秒
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;

    /**
     * 是否记录方法参数
     */
    boolean logArgs() default false;

    /**
     * 是否记录返回值
     */
    boolean logResult() default false;

    /**
     * 警告阈值，超过此时间会记录为WARN级别
     */
    long warnThreshold() default -1;

    /**
     * 是否启用详细日志（记录开始执行）
     */
    boolean enableDetail() default false;

}