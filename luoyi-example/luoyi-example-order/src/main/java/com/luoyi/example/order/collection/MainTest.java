package com.luoyi.example.order.collection;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MainTest {

    public static void main(String[] args) {

        String str = "[{\"field\":\"platformBrandIds\",\"type\":\"equal\",\"values\":[\"125\",\"124\",\"126\"]},{\"field\":\"platformCategoryCodes\",\"type\":\"ne\",\"values\":[\"L132676\",\"L132684\",\"L133758\",\"L133751\",\"L133750\",\"L133347\",\"L133352\"]},{\"field\":\"chainSettlePrice\",\"type\":\"gt\",\"values\":[\"1000\"]},{\"field\":\"standardSalePrice\",\"type\":\"lt\",\"values\":[\"10000\"]},{\"field\":\"profitRate\",\"type\":\"range\",\"values\":[\"0\",\"0.5\"]}]";
        List<Condition> conditions = parseCondition(str);

        ConditionEntity conditionEntity = new ConditionEntity();
        conditionEntity.setPlatformBrandIds("125");
        conditionEntity.setPlatformCategoryCodes("L1337501");
        conditionEntity.setChainSettlePrice(2000L);
        conditionEntity.setStandardSalePrice(9000L);
        conditionEntity.setProfitRate("0.2");

        boolean isValid = ConditionEvaluator.checkConditions(conditionEntity, conditions);
        System.out.println(isValid);


//        String str = "[{\"field\":\"supplierCodes\",\"type\":\"equal\",\"values\":[\"TMV2_3718\"]},{\"field\":\"profitRate\",\"type\":\"gt\",\"values\":[\"0.1\"]},{\"field\":\"platformCategoryCodes\",\"type\":\"equal\",\"values\":[\"L59254\",\"L60335\",\"L133141\",\"L133155\",\"L133175\",\"L133173\",\"L133231\",\"L133202\",\"L133742\",\"L133064\",\"L133069\",\"L133071\",\"L133103\",\"L59959\",\"L59967\",\"L133365\",\"L133301\",\"L59877\",\"L59894\",\"L59903\",\"L59921\",\"L133296\",\"L133320\",\"L133128\"]},{\"field\":\"standardSalePrice\",\"type\":\"lt\",\"values\":[\"80000\"]},{\"field\":\"createTime\",\"type\":\"lt\",\"values\":[\"2025-09-30 23:59:59\"]}]";
//        List<Condition> conditions = parseCondition(str);
//
//        ConditionEntity1 conditionEntity1 = new ConditionEntity1();
//        conditionEntity1.setSupplierCodes("TMV2_3718");
//        conditionEntity1.setPlatformCategoryCodes("L133173");
//        conditionEntity1.setProfitRate("0.2");
//        conditionEntity1.setStandardSalePrice(70000L);
//        conditionEntity1.setCreateTime(DateUtil.parseLocalDateTime("2025-08-30 23:59:59"));
//
//        boolean isValid = ConditionEvaluator.checkConditions(conditionEntity1, conditions);
//        System.out.println(isValid);

    }

    public static List<Condition> parseCondition(String jsonCondition) {

        return JSON.parseObject(jsonCondition, new TypeReference<List<Condition>>() {});
    }

}
