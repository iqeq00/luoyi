package com.luoyi.example.mybatis.model.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("state_machine")
public class StateMachine {
    @TableId("machine_id")
    private String machineId;
    private String state;
    private byte[] stateMachineContext;
}
