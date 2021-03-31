package demo.statemachine;

import demo.statemachine.Enumerate.FormEvents;
import demo.statemachine.Enumerate.FormStates;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
public class FormStateMachineBuilder {
    private final static String MACHINEID = "formMachine";

    public StateMachine<FormStates, FormEvents> build(BeanFactory beanFactory) throws Exception{
        StateMachineBuilder.Builder<FormStates, FormEvents> builder = StateMachineBuilder.builder();

        System.out.println("构建表单状态机");

        builder.configureConfiguration()
                .withConfiguration()
                .machineId(MACHINEID)
                .beanFactory(beanFactory);

        builder.configureStates()
                .withStates()
                .initial(FormStates.BLANK_FORM)
                .states(EnumSet.allOf(FormStates.class));

        builder.configureTransitions()
                .withExternal()
                .source(FormStates.BLANK_FORM).target(FormStates.FULL_FORM)
                .event(FormEvents.WRITE)
                .and()
                .withExternal()
                .source(FormStates.FULL_FORM).target(FormStates.CONFIRM_FORM)
                .event(FormEvents.CONFIRM)
                .and()
                .withExternal()
                .source(FormStates.CONFIRM_FORM).target(FormStates.SUCCESS_FORM)
                .event(FormEvents.SUBMIT);

        return builder.build();
    }


}
