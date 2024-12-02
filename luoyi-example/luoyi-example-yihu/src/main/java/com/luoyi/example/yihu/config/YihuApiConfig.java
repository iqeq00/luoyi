package com.luoyi.example.yihu.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class YihuApiConfig {

    @Value("${yihu.appcode}")
    private String appCode;

    @Value("${yihu.appkey}")
    private String appKey;

    @Value("${yihu.host}")
    private String host;

    @Value("${yihu.api.login}")
    private String login;

    @Value("${yihu.api.getToken}")
    private String getToken;

    @Value("${yihu.api.createCard}")
    private String createCard;

}
