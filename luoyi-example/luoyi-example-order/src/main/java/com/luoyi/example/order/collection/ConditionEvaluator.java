package com.luoyi.example.order.collection;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.json.JSONUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * 条件评估器，执行对象条件验证的入口
 *
 * @author yaojinchi
 */
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConditionEvaluator {

    /**
     * 检查对象是否满足所有条件
     *
     * @param obj        待验证的Java对象
     * @param conditions 条件列表
     * @return 满足所有条件返回true，否则返回false
     */
    public static boolean checkConditions(Object obj, List<Condition> conditions) {

        return Optional.ofNullable(conditions).map(condList -> condList.stream().allMatch(condition -> checkSingleCondition(obj, condition))).orElse(true);
    }

    /**
     * 检查对象是否满足单个条件
     * 流程：获取字段值 -> 解析条件类型 -> 获取验证函数 -> 执行验证
     */
    public static boolean checkSingleCondition(Object obj, Condition condition) {

//        System.out.println(JSONUtil.toJsonStr(condition));
        return getFieldValue(obj, condition.getField()).flatMap(fieldValue -> getConditionType(condition.getType())
            .flatMap(type -> getValidatorFunction(type).map(validator -> validator.apply(fieldValue, condition.getValues())))).orElse(false);
    }

    /**
     * 反射获取对象字段值
     */
    private static Optional<Object> getFieldValue(Object obj, String fieldName) {

        return Optional.ofNullable(obj).flatMap(o -> findField(o.getClass(), fieldName).map(field -> {
            try {
                field.setAccessible(true);
                return field.get(o);
            } catch (Exception e) {
                return null;
            }
        }));
    }

    /**
     * 查找字段
     */
    private static Optional<Field> findField(Class<?> clazz, String fieldName) {

        if (ObjUtil.isNull(clazz) || Object.class.equals(clazz) || ObjUtil.isNull(fieldName)) {
            return Optional.empty();
        }
        try {
            return Optional.of(clazz.getDeclaredField(fieldName));
        } catch (NoSuchFieldException e) {
            return findField(clazz.getSuperclass(), fieldName);
        }
    }

    /**
     * 解析条件类型
     */
    private static Optional<ConditionType> getConditionType(String typeCode) {

        return ConditionType.fromCode(typeCode);
    }

    /**
     * 获取条件对应的验证函数
     */
    private static Optional<BiFunction<Object, List<String>, Boolean>> getValidatorFunction(ConditionType type) {

        return Optional.ofNullable(ConditionFunctionFactory.getFunction(type));
    }

}