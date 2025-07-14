//package v3.listener;
//
//import org.springframework.statemachine.StateMachineContext;
//import org.springframework.statemachine.StateMachinePersist;
//import org.springframework.stereotype.Component;
//import v3.enums.OrderEvent;
//import v3.enums.OrderStatus;
//
//@Component
//public class OrderStateMachinePersist implements StateMachinePersist<OrderStatus, OrderEvent, String> {
//
//    @Override
//    public void write(StateMachineContext<OrderStatus, OrderEvent> context, String orderId) {
//        // 将状态保存到数据库
//        System.out.println("保存订单 " + orderId + " 的状态: " + context.getState());
//    }
//
//    @Override
//    public StateMachineContext<OrderStatus, OrderEvent> read(String orderId) {
//        // 从数据库加载状态
//        System.out.println("加载订单 " + orderId + " 的状态");
//        return null; // 实际实现中返回从数据库读取的上下文
//    }
//}
