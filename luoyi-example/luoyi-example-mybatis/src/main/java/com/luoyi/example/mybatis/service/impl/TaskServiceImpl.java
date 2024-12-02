package com.luoyi.example.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luoyi.example.mybatis.mapper.TaskMapper;
import com.luoyi.example.mybatis.model.entity.Task;
import com.luoyi.example.mybatis.service.TaskService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lichee
 * @since 2020-12-22
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

}
