package com.luoyi.example.order.bean;

import cn.hutool.json.JSONUtil;
import org.springframework.beans.BeanUtils;

public class MainTest01 {

    public static void main(String[] args) {

        SearchProductDTO searchProductDTO = new SearchProductDTO();
        searchProductDTO.setDesc("123");
        searchProductDTO.setPageNum(1);
        searchProductDTO.setPageSize(10);
        QuerySelectionProductOpenBO querySelectionProductOpenBO = new QuerySelectionProductOpenBO();
        BeanUtils.copyProperties(searchProductDTO, querySelectionProductOpenBO);
        System.out.println(querySelectionProductOpenBO);
    }
}
