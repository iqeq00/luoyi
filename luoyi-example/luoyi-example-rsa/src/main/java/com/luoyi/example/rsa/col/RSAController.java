package com.luoyi.example.rsa.col;

import com.luoyi.example.rsa.cpt.RSAUtil;
import com.luoyi.example.rsa.vo.DecryptData;
import com.luoyi.example.rsa.vo.EncryptData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RSAController {

    @PostMapping("/rsa/encrypt")
    public String encrypt(@RequestBody EncryptData encryptData) {

        return RSAUtil.encrypt(encryptData.getPublicKey(), encryptData.getData());
    }

    @PostMapping("/rsa/decrypt")
    public String decrypt(@RequestBody DecryptData encryptData) {

        return RSAUtil.decrypt(encryptData.getPrivateKey(), encryptData.getData());
    }

}