package com.luoyi.example.mybatis.col;

import cn.hutool.core.bean.BeanUtil;
import com.luoyi.example.mybatis.model.entity.Task;
import com.luoyi.example.mybatis.model.vo.TaskVO;
import com.luoyi.example.mybatis.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lichee
 * @since 2020-12-04
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

//    /**
//     * 获取任务列表
//     */
//    @GetMapping("/page")
//    public TableDataInfo page(Task task) {
//
//        startPage();
//        LambdaQueryChainWrapper<Task>  query =  taskService.lambdaQuery();
//        if (StringUtils.isNotBlank(task.getTaskName())) {
//            query.like(Task::getTaskName, task.getTaskName());
//        }
//        if (null != task.getTaskType()) {
//            query.eq(Task::getTaskType, task.getTaskType());
//        }
//        if (null != task.getCreateTime()) {
//            query.le(Task::getCreateTime, task.getCreateTime());
//        }
//        return getDataTable(query.list());
//    }
//
//    /**
//     * 根据任务编号获取详细信息
//     */
//    @GetMapping(value = "/{id}")
//    public AjaxResult getInfo(@PathVariable Integer id) {
//
//        return AjaxResult.success(taskService.getById(id));
//    }

    /**
     * 新增任务
     */
    @PostMapping
    public String add(@Validated @RequestBody TaskVO taskVO) {

        Task task = BeanUtil.copyProperties(taskVO, Task.class);
        taskService.save(task);
        return "success";
    }

    /**
     * 修改任务
     */
    @PutMapping
    public String edit(@Validated @RequestBody TaskVO taskVO) {

        Task task = BeanUtil.copyProperties(taskVO, Task.class);
        taskService.saveOrUpdate(task);
        return "success";
    }

//    /**
//     * 删除任务
//     */
//    @DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable List<Integer> ids) {
//
//        return toAjax(taskService.removeByIds(ids));
//    }
}