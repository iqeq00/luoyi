package com.luoyi.example.rsa.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class EncryptData implements Serializable {

    public String publicKey;

    public String data;
}
