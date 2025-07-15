package com.luoyi.example.mybatis.col;

import com.luoyi.example.mybatis.enums.OrderEvent;
import com.luoyi.example.mybatis.service.biz.OrderStateMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderStateMachineService smService;

    @PostMapping("/{id}/create")
    public void create(@PathVariable String id) {
        smService.sendEvent(id, OrderEvent.CREATE_ORDER);
    }

    @PostMapping("/{id}/pay")
    public void pay(@PathVariable String id) {
        smService.sendEvent(id, OrderEvent.PAY);
    }

    @PostMapping("/{id}/cancel")
    public void cancel(@PathVariable String id) {
        smService.sendEvent(id, OrderEvent.CANCEL_ORDER);
    }
}
