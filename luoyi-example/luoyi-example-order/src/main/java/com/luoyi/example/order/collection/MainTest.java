package com.luoyi.example.order.collection;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;

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
    }

    public static List<Condition> parseCondition(String jsonCondition) {

        return JSON.parseObject(jsonCondition, new TypeReference<List<Condition>>() {});
    }

}
