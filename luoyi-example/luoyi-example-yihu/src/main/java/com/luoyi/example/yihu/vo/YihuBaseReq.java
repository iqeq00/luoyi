package com.luoyi.example.yihu.vo;

import lombok.Data;

@Data
public class YihuBaseReq {

    /**
     * 随机字符串
     */
    public String noncestr;

    /**
     * 签名(参照生成签名说明文档)
     */
    public String sign;

    /**
     * 签名方式，目前支持MD5
     */
    public String signType;

    /**
     * 由健康服务系统分配给第三方应用的标识
     */
    public String appCode;

}
