package com.luoyi.example.rsa.cpt;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.KeyType;

public class RSAUtil {

    public static String encrypt(String publicKey, String data) {

        AsymmetricCrypto rsa = SecureUtil.rsa(null, publicKey);
        return rsa.encryptBase64(data, KeyType.PublicKey);
    }


    public static String decrypt(String data, String privateKey) {
        AsymmetricCrypto rsa = SecureUtil.rsa(privateKey, null);
        return rsa.decryptStr(data, KeyType.PrivateKey);
    }
}
