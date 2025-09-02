package com.luoyi.framework.log.config;

import com.luoyi.framework.log.aspect.TimeTrackAspect;
import com.luoyi.framework.log.properties.LogProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志监控自动配置
 */
@Configuration
@EnableConfigurationProperties(LogProperties.class)
@ConditionalOnProperty(prefix = "luoyi.log", name = "enabled", havingValue = "true", matchIfMissing = true)
public class LogAutoConfiguration {

    @Bean
    public TimeTrackAspect timeTrackAspect() {
        return new TimeTrackAspect();
    }
}