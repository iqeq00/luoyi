package com.luoyi.example.mybatis.test;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

import java.security.SecureRandom;

public class MainTest {

    public static void main(String[] args) {




        // 生成新的 32 位随机密钥
        String newKey32 = RandomUtil.randomString(32);
        System.out.println(newKey32);

        String key32 = cn.hutool.core.util.RandomUtil.randomString(
                "abcdefghijklmnopqrstuvwxyz0123456789", 32);
        System.out.println(key32);
    }
}
