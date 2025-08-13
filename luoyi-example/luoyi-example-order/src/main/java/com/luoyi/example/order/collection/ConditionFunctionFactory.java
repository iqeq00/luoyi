package com.luoyi.example.order.collection;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;
/**
 * 条件验证函数工厂
 *
 * @author yaojinchi
 */
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConditionFunctionFactory {

    // 存储条件类型与验证函数的映射
    private static final Map<ConditionType, BiFunction<Object, List<String>, Boolean>> FUNCTION_MAP = new ConcurrentHashMap<>();

    // 静态初始化默认验证函数
    static {
        registerDefaultFunctions();
    }

    /**
     * 注册默认验证函数
     */
    private static void registerDefaultFunctions() {

        registerFunction(ConditionType.EQUAL, ConditionFunctionFactory::checkEqual);
        registerFunction(ConditionType.NOT_EQUAL, ConditionFunctionFactory::checkNotEqual);
        registerFunction(ConditionType.RANGE, ConditionFunctionFactory::checkRange);
        registerFunction(ConditionType.GREATER_THAN, ConditionFunctionFactory::checkGreaterThan);
        registerFunction(ConditionType.LESS_THAN, ConditionFunctionFactory::checkLessThan);
        registerFunction(ConditionType.GREATER_THAN_OR_EQUAL, ConditionFunctionFactory::checkGreaterThanOrEqual);
        registerFunction(ConditionType.LESS_THAN_OR_EQUAL, ConditionFunctionFactory::checkLessThanOrEqual);
    }

    /**
     * 注册自定义验证函数
     */
    public static void registerFunction(ConditionType type, BiFunction<Object, List<String>, Boolean> function) {

        Optional.ofNullable(type).ifPresent(t -> Optional.ofNullable(function).ifPresent(f -> FUNCTION_MAP.put(t, f)));
    }

    /**
     * 获取条件对应的验证函数
     */
    public static BiFunction<Object, List<String>, Boolean> getFunction(ConditionType type) {

        return FUNCTION_MAP.get(type);
    }

    // ------------------------------ 条件验证逻辑 ------------------------------

    /**
     * equal条件：判断字段值是否在values列表中
     */
    private static boolean checkEqual(Object fieldValue, List<String> values) {

        if (CollectionUtil.isEmpty(values)) {
            return false;
        }
        if (ObjUtil.isNull(fieldValue)) {
            return false;
        }
        return values.stream().anyMatch(value -> isValueMatch(fieldValue, value));
    }

    /**
     * not equal条件：判断字段值是否不在values列表中
     */
    private static boolean checkNotEqual(Object fieldValue, List<String> values) {

        return !checkEqual(fieldValue, values);
    }

    /**
     * range条件：判断字段值是否在values[0]和values[1]之间（包含边界）
     */
    private static boolean checkRange(Object fieldValue, List<String> values) {

        return Optional.ofNullable(values).filter(vals -> vals.size() >= 2)
            .flatMap(vals -> compareWithValue(fieldValue, vals.get(0), val -> val >= 0)
            .flatMap(gteMin -> compareWithValue(fieldValue, vals.get(1), val -> val <= 0)
            .map(lteMax -> gteMin && lteMax))).orElse(false);
    }

    /**
     * gt条件：判断字段值是否大于values[0]
     */
    private static boolean checkGreaterThan(Object fieldValue, List<String> values) {

        return checkComparison(fieldValue, values, val -> val > 0);
    }

    /**
     * lt条件：判断字段值是否小于values[0]
     */
    private static boolean checkLessThan(Object fieldValue, List<String> values) {

        return checkComparison(fieldValue, values, val -> val < 0);
    }

    /**
     * ge条件：判断字段值是否大于等于values[0]
     */
    private static boolean checkGreaterThanOrEqual(Object fieldValue, List<String> values) {

        return checkComparison(fieldValue, values, val -> val >= 0);
    }

    /**
     * le条件：判断字段值是否小于等于values[0]
     */
    private static boolean checkLessThanOrEqual(Object fieldValue, List<String> values) {

        return checkComparison(fieldValue, values, val -> val <= 0);
    }

    // ------------------------------ 通用工具方法 ------------------------------

    /**
     * 通用比较逻辑
     */
    private static boolean checkComparison(Object fieldValue, List<String> values, Function<Integer, Boolean> comparator) {

        return Optional.ofNullable(values).filter(vals -> CollectionUtil.isNotEmpty(vals)).flatMap(vals -> compareWithValue(fieldValue, vals.get(0), comparator)).orElse(false);
    }

    /**
     * 与单个值比较
     */
    private static Optional<Boolean> compareWithValue(Object fieldValue, String value, Function<Integer, Boolean> comparator) {

        return getComparisonResult(fieldValue, value).map(comparator);
    }

    /**
     * 获取比较结果
     */
    private static Optional<Integer> getComparisonResult(Object fieldValue, String value) {

        Optional<Integer> numberCompare = compareAsNumbers(fieldValue, value);
        if (numberCompare.isPresent()) {
            return numberCompare;
        }
        Optional<Integer> dateCompare = compareAsDates(fieldValue, value);
        if (dateCompare.isPresent()) {
            return dateCompare;
        }
        return compareAsStrings(fieldValue, value);
    }

    /**
     * 检查字段值与单个条件值是否匹配
     */
    private static boolean isValueMatch(Object fieldValue, String value) {

        if (ObjUtil.isNull(fieldValue) || ObjUtil.isNull(value)) {
            return false;
        }
        Optional<Integer> numberCompare = compareAsNumbers(fieldValue, value);
        if (numberCompare.isPresent()) {
            return numberCompare.get() == 0;
        }
        Optional<Integer> dateCompare = compareAsDates(fieldValue, value);
        if (dateCompare.isPresent()) {
            return dateCompare.get() == 0;
        }
        return fieldValue.toString().equals(value);
    }

    /**
     * 数字比较
     */
    private static Optional<Integer> compareAsNumbers(Object fieldValue, String value) {

        return convertToBigDecimal(fieldValue).flatMap(fieldNum -> convertToBigDecimal(value).map(valueNum -> fieldNum.compareTo(valueNum)));
    }

    /**
     * 时间比较
     */
    private static Optional<Integer> compareAsDates(Object fieldValue, String value) {

        try {
            DateTime fieldDate = DateUtil.parse(fieldValue.toString());
            DateTime valueDate = DateUtil.parse(value);
            return Optional.of(fieldDate.compareTo(valueDate));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * 字符串比较
     */
    private static Optional<Integer> compareAsStrings(Object fieldValue, String value) {

        return Optional.ofNullable(fieldValue).map(Object::toString).map(fieldStr -> value != null ? fieldStr.compareTo(value) : 1);
    }

    /**
     * 转换为BigDecimal
     */
    private static Optional<BigDecimal> convertToBigDecimal(Object value) {

        return Optional.ofNullable(value).map(Object::toString).filter(str -> StrUtil.isNotBlank(str.trim())).flatMap(str -> {
            try {
                return Optional.of(new BigDecimal(str));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        });
    }

}