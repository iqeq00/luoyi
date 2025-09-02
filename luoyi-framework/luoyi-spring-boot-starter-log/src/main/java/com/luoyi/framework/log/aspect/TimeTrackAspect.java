package com.luoyi.framework.log.aspect;

import com.luoyi.framework.log.annotation.TimeTrack;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 时间追踪切面
 */
@Slf4j
@Aspect
@Component
public class TimeTrackAspect {

    @Around("@annotation(timeTrack)")
    public Object around(ProceedingJoinPoint joinPoint, TimeTrack timeTrack) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String desc = timeTrack.value().isEmpty() ? className + "." + methodName : timeTrack.value();

        if (timeTrack.enableDetail()) {
            log.debug("{} 开始执行...", desc);
        }

        long startTime = System.nanoTime();
        try {
            Object result = joinPoint.proceed();
            long duration = System.nanoTime() - startTime;
            logExecution(timeTrack, desc, duration, joinPoint.getArgs(), result);
            return result;
        } catch (Throwable e) {
            long duration = System.nanoTime() - startTime;
            logError(timeTrack, desc, duration, e);
            throw e;
        }
    }

    private void logExecution(TimeTrack timeTrack, String desc, long durationNanos,
                              Object[] args, Object result) {
        long duration = timeTrack.unit().convert(durationNanos, TimeUnit.NANOSECONDS);
        String unit = timeTrack.unit().toString().toLowerCase();

        StringBuilder message = new StringBuilder()
                .append(desc).append(" 耗时: ").append(duration).append(" ").append(unit);

        if (timeTrack.logArgs()) {
            message.append(", 参数: ").append(Arrays.toString(args));
        }

        if (timeTrack.logResult() && result != null) {
            message.append(", 返回值: ").append(abbreviate(result.toString(), 100));
        }

        if (timeTrack.warnThreshold() > 0 && duration > timeTrack.warnThreshold()) {
            log.warn(message.toString());
        } else {
            log.info(message.toString());
        }
    }

    private void logError(TimeTrack timeTrack, String desc, long durationNanos, Throwable e) {
        long duration = timeTrack.unit().convert(durationNanos, TimeUnit.NANOSECONDS);
        String unit = timeTrack.unit().toString().toLowerCase();
        log.error("{} 执行失败, 耗时: {} {}, 异常: {}", desc, duration, unit, e.getMessage());
    }

    private String abbreviate(String str, int maxLength) {
        if (str == null || str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength) + "...";
    }
}