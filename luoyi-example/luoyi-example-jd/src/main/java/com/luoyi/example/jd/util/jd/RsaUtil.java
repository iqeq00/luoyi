package com.luoyi.example.jd.util.jd;


import org.apache.commons.codec.binary.Base64;

public class RsaUtil {
    public RsaUtil() {
    }

    public static byte[] decryptBASE64(String key) {
        return Base64.decodeBase64(key);

    }

    public static String encryptBASE64(byte[] key) {
        return Base64.encodeBase64String(key);
    }
}
