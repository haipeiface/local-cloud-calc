package demo.statemachine;

import demo.statemachine.Enumerate.OrderEvents;
import demo.statemachine.Enumerate.OrderStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private StateMachine<OrderStates, OrderEvents> orderSingleMachine;

    @Override
    public void run(String... args) throws Exception {
        orderSingleMachine.start();
        orderSingleMachine.sendEvent(OrderEvents.PAY);
        orderSingleMachine.sendEvent(OrderEvents.RECEIVE);
        System.out.println("final state " + orderSingleMachine.getState());
    }
}
