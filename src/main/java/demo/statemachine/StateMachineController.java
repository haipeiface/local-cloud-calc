package demo.statemachine;

import demo.statemachine.Enumerate.OrderEvents;
import demo.statemachine.Enumerate.OrderStates;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statemachine")
public class StateMachineController {

    @Autowired
    private StateMachine orderSingleMachine;

    @Autowired
    private OrderStateMachineBuilder orderStateMachineBuilder;

    @Autowired
    private BeanFactory beanFactory;

    @RequestMapping("/testOrderState")
    public void testOrderState(String orderId) throws Exception {

        StateMachine<OrderStates, OrderEvents> stateMachine = orderStateMachineBuilder.build(beanFactory);

        // 创建流程
        stateMachine.start();

        // 触发PAY事件
        stateMachine.sendEvent(OrderEvents.PAY);

        // 触发RECEIVE事件
        stateMachine.sendEvent(OrderEvents.RECEIVE);


        // 获取最终状态
        System.out.println("最终状态：" + stateMachine.getState().getId());
    }
}
