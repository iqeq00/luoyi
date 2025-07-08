//package com.luoyi.example.rsa.main;
//
//import cn.hutool.crypto.SecureUtil;
//
//import java.security.KeyPair;
//
//
//public class RSAUtils {
//
//    public static void main(String[] args) {
//
//        // 生成RSA密钥对，默认密钥长度为1024
////        RSA rsa = new RSA(2048);
////
////        // 获取公钥和私钥
////        String publicKey = Base64.encode(rsa.getPublicKey().getEncoded());
////        String privateKey = Base64.encode(rsa.getPrivateKey().getEncoded());
////
////        // 输出密钥对
////        System.out.println("RSA公钥(Base64编码):\n" + publicKey);
////        System.out.println("\nRSA私钥(Base64编码):\n" + privateKey);
//
//        KeyPair rsa = SecureUtil.generateKeyPair("RSA", 2048);
//        String privateKeyBase64 = SecureUtil.encodeBase64(rsa.getPrivate().getEncoded());
//        String publicKeyBase64 = SecureUtil.encodeBase64(rsa.getPublic().getEncoded());
//
//        System.out.println("私钥（Base64）: " + privateKeyBase64);
//        System.out.println("公钥（Base64）: " + publicKeyBase64);
//    }
//}
