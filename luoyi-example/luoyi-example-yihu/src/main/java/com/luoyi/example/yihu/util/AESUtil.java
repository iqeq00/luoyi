package com.luoyi.example.yihu.util;

public class AESUtil {

    /**
     * 字符编码
     */
    private static final String CHARSET_NAME   = "utf-8";
    /**
     * 加密方式
     */
    private static final String ALGORITHM      = "AES";
    /**
     * "算法/模式/补码方式"
     */
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    /**
     * AES 加密
     *
     * @param sSrc 待加密的原字符串
     * @param sKey 秘钥
     * @return 处理结果
     */
    public static String encrypt(String sSrc, String sKey) {
        log.info("待加密字符串:{}", sSrc);
        String result = null;
        try {
            if (sKey == null) {
                log.info("Key为空null");
                return result;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                log.info("Key长度不是16位");
                return result;
            }
            byte[] raw = sKey.getBytes(CHARSET_NAME);
            // 指定密钥规则，参数一：密钥key的字节数字，参数二：算法
            SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
            // 获取加密对象
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            // 初始化加密 参数一：模式加密 参数二：密钥规则
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            //进行加密
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

            //此处使用BASE64做转码功能，同时能起到2次加密的作用。
            result = new Base64().encodeToString(encrypted);
        } catch (Exception e) {
            log.error("AES加密处理异常:", e);
        }
        return result;

    }

    /**
     * AES解密
     *
     * @param sSrc 待解密的字符串
     * @param sKey 解密秘钥
     * @return 解密后数据
     */
    public static String decrypt(String sSrc, String sKey) {
        log.info("AES decrypt 待解密字符串:{}", sSrc);
        try {
            // 判断Key是否正确
            if (sKey == null) {
                log.info("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                log.info("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes(CHARSET_NAME);
            // 指定密钥规则，参数一：密钥key的字节数字，参数二：算法
            SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
            // 获取加密对象
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            // 初始化加密 参数一：模式解密 参数二：密钥规则
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            // BASE64 解码
            byte[] encrypted1 = new Base64().decode(sSrc);
            // 解密
            byte[] original = cipher.doFinal(encrypted1);

            String originalString = new String(original, CHARSET_NAME);
            log.info("AES decrypt 解密返回:{}", originalString);
            return originalString;
        } catch (Exception e) {
            log.error("AES解密处理异常:", e);
            return null;
        }
    }
}
