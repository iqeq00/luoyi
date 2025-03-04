package com.luoyi.example.order;

import com.luoyi.example.order.dto.CardOrderDTO;
import com.luoyi.example.order.dto.EntityOrderDTO;
import com.luoyi.example.order.enums.OrderType;
import com.luoyi.example.order.service.CardOrderService;
import com.luoyi.example.order.service.EntityOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LuoyiExampleOrderApplicationTests {

    @Autowired
    public CardOrderService cardOrderService;

    @Autowired
    public EntityOrderService entityOrderService;

    @Test
    void contextLoads() {
        CardOrderDTO cardOrderDTO = new CardOrderDTO();
        cardOrderDTO.setSceneType(OrderType.CARD.name());
        cardOrderService.createOrder(cardOrderDTO);
    }

    @Test
    void contextLoads1() {
        EntityOrderDTO entityOrderDTO = new EntityOrderDTO();
        entityOrderDTO.setSceneType(OrderType.ENTITY.name());
        entityOrderService.createOrder(entityOrderDTO);
    }

}
