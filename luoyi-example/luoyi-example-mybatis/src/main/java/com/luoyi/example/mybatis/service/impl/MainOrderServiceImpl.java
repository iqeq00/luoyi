package com.luoyi.example.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luoyi.example.mybatis.mapper.MainOrderMapper;
import com.luoyi.example.mybatis.model.entity.MainOrder;
import com.luoyi.example.mybatis.service.MainOrderService;
import org.springframework.stereotype.Service;

@Service
public class MainOrderServiceImpl extends ServiceImpl<MainOrderMapper, MainOrder> implements MainOrderService {

}
