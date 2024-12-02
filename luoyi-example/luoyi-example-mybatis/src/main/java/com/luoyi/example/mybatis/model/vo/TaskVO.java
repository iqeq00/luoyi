package com.luoyi.example.mybatis.model.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author lichee
 * @since 2020-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TaskVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务描述
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String taskDesc;

    /**
     * 任务类型: 1.普通任务 2.重点任务 3.紧急任务
     */
    private Integer taskType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
