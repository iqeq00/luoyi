package com.luoyi.example.rsa.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DecryptData implements Serializable {

    public String privateKey;

    public String data;
}
