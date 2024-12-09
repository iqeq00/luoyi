package com.luoyi.example.jd.vo;

import lombok.Data;

@Data
public class JLTokenInfo {
    private String access_token;
    private String uid;
    private String refresh_token;
    private Long refresh_token_expires;
    private Long time;
    private Long expires_in;
}