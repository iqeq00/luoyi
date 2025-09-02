package com.luoyi.example.card.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 加密和解密
 *
 * @author lichee
 */
@Slf4j
public class EncryptUtil {

    /**
     * 解密敏感字段
     */
    public static String getDecryptStr(String key, String encrypt, String salt) {

        if (Objects.nonNull(encrypt)) {
            try {
                String newKey = key;
                if (StrUtil.isNotBlank(salt)) {
                    newKey = newKey.substring(0, newKey.length() - 6) + salt;
                }
                SM4 sm4 = SmUtil.sm4(newKey.getBytes(StandardCharsets.UTF_8));
                return sm4.decryptStr(encrypt);
            } catch (Exception e) {
                log.error("供应商三方信息解密异常：", e);
                return null;
            }
        }
        return null;
    }

    /**
     * 加密敏感字段
     */
    public static String getEncryptStr(String key, String needEncrypt, String salt) {

        if (Objects.nonNull(needEncrypt)) {
            try {
                String newKey = key;
                if (StrUtil.isNotBlank(salt)) {
                    newKey = newKey.substring(0, newKey.length() - 6) + salt;
                }
                SM4 sm4 = SmUtil.sm4(newKey.getBytes(StandardCharsets.UTF_8));
                return new String(sm4.encryptHex(needEncrypt, StandardCharsets.UTF_8));
            } catch (Exception e) {
                log.error("供应商三方信息加密异常：", e);
                return null;
            }
        }
        return null;
    }

    /**
     * 解密
     * @return
     */
    public static String decrypt(String key, String code){
        SM4 sm4 = SmUtil.sm4(key.getBytes(StandardCharsets.UTF_8));
        return sm4.decryptStr(code);
    }

    public static void main(String[] args) {
        String key = "sgh1234560010001";
        String str = getEncryptStr(key, "asdfghjkl", "qr0bzt");
        System.out.println(str);
//        System.out.println(getDecryptStr(key, str, "qr0bzt"));
        String str1 = getEncryptStr(key, "zxcvbnm", "wpk3l1");
        System.out.println(str1);
        System.out.println("===========");
        System.out.println(decrypt(key,"490a4b11b04ffb4f2bd61f12b9925c3a239e451c6dbe82795164a16e507848ae"));
    }

}