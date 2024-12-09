package com.luoyi.example.jd.util.jd;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 京东锦礼 工具类
 *
 * @author: chenlong
 * @date: 2024/1/17 10:09
 */
@Slf4j
public class JdSignUtil {

    /**
     * 添加签名
     * @param
     * @return
     */
    public static void addSign(TreeMap<String, Object> params, String rsaSignPrivateKey) {
        //组装sign数据
        String s1 = sortParams(params);
        log.info("s1= {}", s1);
        String s2 = ShaUtil.encrypt(s1, "SHA-256");
        log.info("s2= {}", s2);
        byte[] s2Rsa;
        String sign;
        try {
            s2Rsa = RsaCoder.encryptByPrivateKey(s2.getBytes("UTF-8"), rsaSignPrivateKey);
            sign = RsaUtil.encryptBASE64(s2Rsa);
        } catch (Exception e) {
            log.error("生成签名失败,参数:{},{},异常:{}", JSON.toJSONString(params), JSON.toJSONString(rsaSignPrivateKey), e);
            throw new RuntimeException("获取签名失败");
        }
        log.info("sign= {}", sign);
        params.put("sign", sign);
    }

    /**
     * 构建数据加密方法
     * 是否加密字段中标注为“加密传输”的字段均先采用rsaEncryptPublicKey加密，再通过base64转换为字符串 。
     *
     * @param s
     * @return
     */
    public static String encryptParam(String s, String rsaEncryptPublicKey) {
        try {
            byte[] s1Byte = RsaCoder.encryptByPublicKey(s.getBytes(), rsaEncryptPublicKey);
            String s1 = RsaCoder.encryptBASE64(s1Byte);
            return s1;
        } catch (Exception e) {
            log.error("参数加密失败,srcStr={},异常:{}", s, e);
            throw new RuntimeException("获取签名失败");
        }
    }

    /**
     * 构建数据解密方法
     * <p>
     * 凡标注为“加密传输”的字段均先通过base64 进行解码 ，然后通过rsaEncryptPrivateKey 解密。
     * 所有信息均按照逆向流程处理，商户使用公钥对基本信息加密，商城平台使用私钥对基本信息解密。
     *
     * @param s
     * @return
     */
    public static String decryptParam(String s, String rsaSignPrivateKey) {
        try {
            byte[] s1EncryptedByte = RsaCoder.decryptBASE64(s);
            byte[] s1Byte = RsaCoder.decryptByPrivateKey(s1EncryptedByte, rsaSignPrivateKey);
            return new String(s1Byte);
        } catch (Exception e) {
            log.error("参数解密失败,srcStr={},异常:", s, e);
            throw new RuntimeException("获取签名失败");
        }
    }

    /**
     * 参数列表按照字典排序，并拼接为URL 键值对字符串
     *
     * @param map
     * @return
     */
    private static String sortParams(Map<String, Object> map) {
        // 将参数按照参数名的 ASCII 码顺序从小到大排序（字典序），使用 URL 键值对
        // 的方式拼接成字符串 S1，（如：k1=value1&k2=value2&k3=value3…）
        StringBuilder s1 = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (StringUtils.isNotBlank(String.valueOf(entry.getValue()))) {
                s1.append(entry.getKey()).append("=").append(entry.getValue())
                        .append("&");
            }
        }
        String params = s1.toString();
        // 去掉末尾&号
        if (params.endsWith("&")) {
            String subStr = params.substring(0, params.length() - 1);
            params = subStr;
        }
        return params;
    }

    /**
     * Md5加密
     *
     * @param str
     * @return
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest();
        StringBuilder md5StrBuff = new StringBuilder();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString();
    }
}
