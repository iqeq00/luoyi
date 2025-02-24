package com.luoyi.example.jd.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class SignUtil {

    public static String signRsaWithBean(Object bean, String privateKey) {
        StringBuilder signContent = new StringBuilder();
        Map<String, Object> map = BeanUtil.beanToMap(bean, false,true);
        // 处理对象里面的list
        map.entrySet().stream().forEach(e -> {
            if (e.getValue() instanceof List) {
                StringBuilder str = new StringBuilder("");
                list2string((List<?>) e.getValue(),str);
                map.put(e.getKey(),str);
            }
        });
        List<String> keyList = map.keySet().stream().sorted().collect(Collectors.toList());

        for (String key : keyList) {
            signContent.append(map.get(key));
        }
        log.info("签名参数:{}",signContent);
//        Sign sign = SecureUtil.sign(SignAlgorithm.SHA1withRSA, privateKey, null);
//        return Base64Encoder.encode(sign.sign(String.valueOf(signContent)));
        return signContent.toString();
    }

    /**
     * list对象转String
     * @param value
     * @param str
     */
    private static void list2string(List<?> value, StringBuilder str) {
        value.forEach(v -> {
            if(v instanceof String){
                str.append(v);
            }else if(v instanceof Integer){
                str.append(v);
            }else{
                Map<String, Object> smap = BeanUtil.beanToMap(v, false,true);
                List<String> keyList = smap.keySet().stream().sorted().collect(Collectors.toList());
                for (String key : keyList) {
                    Object object = smap.get(key);
                    if (object instanceof List) {
                        list2string((List<?>) object,str);
                    } else {
                        str.append(object);
                    }
                }
            }
        });
    }

    public static String signRsaWithData(String data, String privateKey) {
        Sign sign = SecureUtil.sign(SignAlgorithm.SHA1withRSA, privateKey, null);
        return Base64Encoder.encode(sign.sign(String.valueOf(data)));
    }

    /**
     * 验证签名
     * @param bean
     * @param publicKey
     * @param sourceSign
     * @return
     */
    public static Boolean verifySign(Object bean, String publicKey,String sourceSign){
        StringBuilder signContent = new StringBuilder();
        Map<String, Object> map = BeanUtil.beanToMap(bean, false,true);
        // 处理对象里面的list
        map.forEach((key1, value) -> {
            if (value instanceof List) {
                StringBuilder str = new StringBuilder("");
                list2string((List<?>) value,str);
                map.put(key1, str);
            }
        });
        List<String> keyList = map.keySet().stream().sorted().collect(Collectors.toList());

        for (String key : keyList) {
            signContent.append(map.get(key));
        }
        log.info("验签拼接原文:{}", signContent);
        Sign sign = SecureUtil.sign(SignAlgorithm.SHA1withRSA, null, publicKey);
        return sign.verify(String.valueOf(signContent).getBytes(), Base64Decoder.decode(sourceSign));
    }

    public static boolean isJsonString(String jsonString) {
        //String pattern = "\\{.*\\}|\\[.*\\]";
        String pattern = "\\{.*\\}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(jsonString);
        return m.matches();
    }

    public static void main(String[] args) {
        RightsPackageInfo info = new RightsPackageInfo();
        info.setRightsPackageCode("RP10000066");
        info.setSceneryIds(new ArrayList<>(Collections.singleton("SC10000001")));
        List<AppointmentDateBO> list = new ArrayList<>();
        AppointmentDateBO bo = new AppointmentDateBO();
        bo.setEndTime("2024-05-22");
        bo.setStartTime("2024-05-25");
        list.add(bo);
        info.setList(list);
        //String v= "{\"rightsPackageCode\":\"RP10000066\",\"appointmentDateBOList\":[{\"startTime\":\"2024-05-22\",\"endTime\":\"2024-05-25\"}],\"sceneryIds\":[\"SC10000001\"]}";
        signRsaWithBean(info,"123");
    }

    @Data
    static class RightsPackageInfo{
        private String rightsPackageCode;
        private List<AppointmentDateBO> list;
        private List<String> sceneryIds;
    }

    @Data
    static class AppointmentDateBO{
        private String startTime;
        private String endTime;
    }
}
