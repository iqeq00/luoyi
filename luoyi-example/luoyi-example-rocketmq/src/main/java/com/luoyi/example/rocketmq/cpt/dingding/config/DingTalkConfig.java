package com.luoyi.example.rocketmq.cpt.dingding.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class DingTalkConfig {

    @Value("${dingers.ding-talk.open-api}")
    private String openApi;

    @Value("${dingers.ding-talk.access-token}")
    private String accessToken;

    @Value("${dingers.ding-talk.prefix}")
    private String prefix;

    @Value("${dingers.ding-talk.secret}")
    private String secret;

    private String algorithm = "HmacSHA256";
}
