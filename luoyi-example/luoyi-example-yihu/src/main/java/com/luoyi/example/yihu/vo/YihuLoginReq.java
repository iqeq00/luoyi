package com.luoyi.example.yihu.vo;

import lombok.Data;

@Data
public class YihuLoginReq extends YihuBaseReq {

    /**
     * 用户是否需要自助登录健康服务系统
     *
     * 关于selfLogin(自助登录)的设置：
     * 若需要用户通过微信或者手机号自助登录健康管理权益系统，则selfLogin需要设置为1；
     * 若每次用户都是通过三方的应用跳转到健康管理权益系统，则selfLogin可以设置为0；
     */
    public String selfLogin;

    /**
     * 第三方应用的唯一用户标识
     */
    public String thirdUserId;

    /**
     * 用户的手机号码
     *
     * 关于mobile的设置：
     * 若selfLogin为1时，mobile为必填，且mobile不可重复；
     * 若selfLogin为0时，用户将无法自助登录，mobile可以不填;
     */
    public String mobile;

    /**
     * 用户的证件类型，101:身份证;111:其它
     *
     * 关于身份信息的设置：
     * 当selfLogin为1时，身份信息可以不传，当用户登陆时系统会要求用户补充实名认证；
     * 当selfLogin为1时，若不希望用户在登陆时再次补充实名认证，则需要在注册时将身份信息传输过来。
     * 若身份证件类型为身份证时，可以不传输性别和出生日期。
     * 若身份证件类型为非身份证时，需要传输性别和出生日期。
     */
    public String certificateType;

    /**
     * 用户的证件号
     */
    public String certificateNo;

    /**
     * 用户的姓名
     */
    public String name;

    /**
     * 用户的性别，0:男;1:女
     */
    public String gender;

    /**
     * 用户的出生日期 格式：yyyy-MM-dd HH:mm:ss
     */
    public String birthDate;

}