package com.tacos.tacocloud.message.controller;

import com.tacos.tacocloud.domain.Order;
import com.tacos.tacocloud.message.consumer.OrderReceiver;
import com.tacos.tacocloud.message.producer.OrderMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/order/message",
        produces = "application/json")
public class OrderMessageController {
    private OrderMessageService service;
    private OrderReceiver receiver;

    @Autowired
    public OrderMessageController(OrderMessageService service, OrderReceiver receiver) {
        this.service = service;
        this.receiver = receiver;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> publish(@RequestBody Order order) {
        service.sendOrder(order);
        return ResponseEntity.ok("Order publish successful");
    }

    @GetMapping
    public ResponseEntity<Order> pull() {
        Order order = receiver.receiveMessage();
        return ResponseEntity.ok(order);
    }
}
