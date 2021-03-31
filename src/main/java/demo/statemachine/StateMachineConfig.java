package demo.statemachine;

import demo.statemachine.Enumerate.OrderEvents;
import demo.statemachine.Enumerate.OrderStates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;


@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStates, OrderEvents> {

    private Logger logger = LoggerFactory.getLogger(getClass());

//    @Override
//    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
//        config.withConfiguration().listener(listener());
//    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderStates, OrderEvents> states) throws Exception {
        states.withStates().initial(OrderStates.UNPAID).states(EnumSet.allOf(OrderStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStates, OrderEvents> transitions) throws Exception {
        transitions
                .withExternal()
                    .source(OrderStates.UNPAID).target(OrderStates.WAITING_FOR_RECEIVE)
                    .event(OrderEvents.PAY)
                    .and()
                .withExternal()
                    .source(OrderStates.WAITING_FOR_RECEIVE).target(OrderStates.DONE)
                    .event(OrderEvents.RECEIVE);
    }

//    @Bean
//    public StateMachineListener<States, Events> listener() {
//        return new StateMachineListenerAdapter<States, Events>() {
//            @Override
//            public void transition(Transition<States, Events> transition) {
//
//                /**what is transition target source guard kind
//                 * if 里不加return 会报错
//                 * 还可写作 @WithStateMachine
//                 * */
//                if(transition.getTarget().getId() == States.UNPAID) {
//                    logger.info("unpaid......");
//
//                }
//
//                if(transition.getSource().getId() == States.UNPAID
//                        && transition.getTarget().getId() == States.WAITING_FOR_RECEIVE) {
//                    logger.info("paid waiting for receive......");
//                    return;
//                }
//
//                if(transition.getSource().getId() == States.WAITING_FOR_RECEIVE
//                        && transition.getTarget().getId() == States.DONE) {
//                    logger.info("received done.......");
//                    return;
//                }
//            }
//        };
//    }
}
