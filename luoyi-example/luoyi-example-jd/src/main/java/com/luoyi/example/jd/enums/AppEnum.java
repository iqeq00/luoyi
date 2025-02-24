package com.luoyi.example.jd.enums;

import lombok.Getter;

/**
 *
 * @author: chenlong
 * @date: 2024/12/26 11:28
 */
@Getter
public enum AppEnum {

    HXT("和信通"),
    SGH("蜀光惠"),
    ;

    private String value;

    AppEnum(String value) {
        this.value = value;
    }

}
