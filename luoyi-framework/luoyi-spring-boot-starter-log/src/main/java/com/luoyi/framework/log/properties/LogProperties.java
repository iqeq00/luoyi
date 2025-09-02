package com.luoyi.framework.log.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.concurrent.TimeUnit;

/**
 * 日志监控配置属性
 */
@Data
@ConfigurationProperties(prefix = "luoyi.log")
public class LogProperties {
    /**
     * 是否启用日志监控功能
     */
    private boolean enabled = true;

    /**
     * 默认时间单位
     */
    private TimeUnit defaultUnit = TimeUnit.MILLISECONDS;

    /**
     * 默认是否记录方法参数
     */
    private boolean defaultLogArgs = false;

    /**
     * 默认是否记录方法返回值
     */
    private boolean defaultLogResult = false;

    /**
     * 全局警告阈值
     */
    private long globalWarnThreshold = -1;
}
