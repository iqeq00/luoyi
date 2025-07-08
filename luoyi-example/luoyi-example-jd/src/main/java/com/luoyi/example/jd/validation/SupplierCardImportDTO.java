package com.luoyi.example.jd.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class SupplierCardImportDTO implements Serializable {

    private static final long serialVersionUID = -4813161117116868051L;

    @NotNull(message = "卡号不能为空")
    @Size(max = 255, message = "卡号长度不能超过255位")
    private String cardNo;

    @NotNull(message = "卡密不能为空")
    @Size(max = 512, message = "卡密长度不能超过512位")
    private String cardPassword;

    @NotNull(message = "兑换码不能为空")
    @Size(max = 512, message = "兑换码长度不能超过512位")
    private String redemptionCode;

    @NotNull(message = "短链不能为空")
    @Size(max = 512, message = "短链长度不能超过512位")
    @ValidShortUrl(message = "短链格式错误")
    private String cardShortLink;

    @NotNull(message = "验证码不能为空")
    @Size(max = 64, message = "验证码长度不能超过64位")
    private String captcha;

    @NotNull(message = "条形码不能为空")
    @Size(max = 512, message = "条形码长度不能超过512 位")
    private String barCode;

    private String legalStartTime;

    private String legalEndTime;

    @NotNull(message = "面值不能为空")
    @ValidMoney(message = "面值格式错误")
    private String cardValue;
}
