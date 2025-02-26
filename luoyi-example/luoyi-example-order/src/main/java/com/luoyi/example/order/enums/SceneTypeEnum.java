package com.luoyi.example.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 供应商类型
 * @author: chenlong
 * @date: 2023/8/26 12:04
 */
@AllArgsConstructor
@Getter
public enum SceneTypeEnum implements IBaseEnum<String> {
    ENTITY("ENTITY","实物订单","实物",PrimacySceneTypeEnum.ENTITY),
    FILM("FILM","电影票订单","电影", PrimacySceneTypeEnum.SERVICE),
    MT_GROUP("MT_GROUP","团购|美团","团购", PrimacySceneTypeEnum.SERVICE),
    MT_WM("MT_WM","外卖|美团","外卖", PrimacySceneTypeEnum.SERVICE),
    HOTEL("HOTEL","文旅|酒店","酒店", PrimacySceneTypeEnum.SERVICE),
    TICKET("TICKET","文旅|景点门票","景点", PrimacySceneTypeEnum.SERVICE),
    CARD("CARD", "卡券充值","卡券", PrimacySceneTypeEnum.SERVICE),
    JDJL("JDJL", "京东锦礼","京东锦礼", PrimacySceneTypeEnum.H5MALL),
    RIGHTS("RIGHTS","权益商品","权益商品", PrimacySceneTypeEnum.SERVICE),
    MOBILE_RECHARGE("MOBILE_RECHARGE", "手机充值","手机充值", PrimacySceneTypeEnum.SERVICE),
    CAKE_FLOWER("CAKE_FLOWER", "蛋糕鲜花", "蛋糕鲜花", PrimacySceneTypeEnum.H5MALL),
    SAM("SAM","山姆沃尔玛","山姆沃尔玛", PrimacySceneTypeEnum.H5MALL),
    REFUEL("REFUEL", "九米加油券", "加油券", PrimacySceneTypeEnum.H5MALL),
    SPORT("SPORT", "运动馆", "运动馆", PrimacySceneTypeEnum.SERVICE),
    QCSE("QCSE", "屈臣氏实物商品", "屈成氏",PrimacySceneTypeEnum.H5MALL),
    ;

    private final String value;
    private final String label;
    private final String simpleLabel;
    private final PrimacySceneTypeEnum primacySceneTypeEnum;

    public static String getSimpleLabel(String value) {
        return Arrays.stream(SceneTypeEnum.values())
                .filter(type -> type.getValue().equals(value))
                .findFirst().get().getSimpleLabel();
    }
    public static Boolean isEntity(String value) {
        PrimacySceneTypeEnum primacySceneTypeEnum = Arrays.stream(SceneTypeEnum.values())
                .filter(type -> type.getValue().equals(value))
                .findFirst().get().getPrimacySceneTypeEnum();
        return primacySceneTypeEnum.equals(PrimacySceneTypeEnum.ENTITY) || primacySceneTypeEnum.equals(PrimacySceneTypeEnum.H5MALL);
    }

    public static Boolean isH5Mall(String value) {
        PrimacySceneTypeEnum primacySceneTypeEnum = Arrays.stream(SceneTypeEnum.values())
                .filter(type -> type.getValue().equals(value))
                .findFirst().get().getPrimacySceneTypeEnum();
        return primacySceneTypeEnum.equals(PrimacySceneTypeEnum.H5MALL);
    }

    public static List<String> getAllH5Mall(){
        return Arrays.stream(SceneTypeEnum.values()).filter(type -> type.getPrimacySceneTypeEnum().equals(PrimacySceneTypeEnum.H5MALL)).map(SceneTypeEnum::getValue).collect(Collectors.toList());
    }

    public static List<String> getAllEntity(){
        return Arrays.stream(SceneTypeEnum.values()).filter(type -> type.getPrimacySceneTypeEnum().equals(PrimacySceneTypeEnum.ENTITY) || type.getPrimacySceneTypeEnum().equals(PrimacySceneTypeEnum.H5MALL)).map(SceneTypeEnum::getValue).collect(Collectors.toList());
    }

    /**
     * 判断是否是实物跟H5商城 （用于展示用户售后寄回模块）
     * @param value
     * @return
     */
    public static Boolean entityAndH5Service(String value) {
        return ENTITY.getValue().equals(value) || CAKE_FLOWER.getValue().equals(value) || SAM.getValue().equals(value);
    }

    public static SceneTypeEnum getInstance(String sceneType){
        SceneTypeEnum[] instances = SceneTypeEnum.values();
        for (SceneTypeEnum instance : instances) {
            if (instance.getValue().equals(sceneType)) {
                return instance;
            }
        }
        return null;
    }
}
