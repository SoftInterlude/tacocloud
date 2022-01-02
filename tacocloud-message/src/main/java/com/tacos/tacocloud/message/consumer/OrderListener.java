package com.tacos.tacocloud.message.consumer;

import com.tacos.tacocloud.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderListener {

    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(Order order) {
        log.info("Receive order by listener: " + order);
    }
}
