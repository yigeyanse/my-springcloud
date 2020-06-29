package com.isoft;

import com.isoft.enums.OrderStatus;
import com.isoft.enums.OrderStatusChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class StateMachineApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StateMachineApplication.class, args);
	}

	@Autowired
	private StateMachine<OrderStatus, OrderStatusChangeEvent> stateMachine;

	//在run函数中，我们定义了整个流程的处理过程
	@Override
	public void run(String... args) throws Exception {
		/*stateMachine.start();    //start()就是创建这个订单流程，根据之前的定义，该订单会处于待支付状态，
		stateMachine.sendEvent(OrderStatusChangeEvent.PAYED);    //通过调用sendEvent(Events.PAY)执行支付操作，
		stateMachine.sendEvent(OrderStatusChangeEvent.RECEIVED);  //通过调用用sendEvent(Events.RECEIVE)来完成收货操作*/
	}

}
