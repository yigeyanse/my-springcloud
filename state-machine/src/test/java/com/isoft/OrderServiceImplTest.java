package com.isoft;

import com.isoft.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void testMultThread(){
        //orderService.create();
        orderService.create();

        orderService.pay(1);
        orderService.deliver(1);
        orderService.receive(1);
        /*new Thread(()->{
            orderService.deliver(1);
            orderService.receive(1);
        }).start();*/

/*        orderService.pay(2);
        orderService.deliver(2);
        orderService.receive(2);*/

        System.out.println(orderService.getOrders());
    }

}
