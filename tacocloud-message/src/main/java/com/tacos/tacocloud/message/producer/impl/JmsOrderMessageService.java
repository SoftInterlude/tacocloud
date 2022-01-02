package com.tacos.tacocloud.message.producer.impl;

import com.tacos.tacocloud.domain.Order;
import com.tacos.tacocloud.message.producer.OrderMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class JmsOrderMessageService implements OrderMessageService {

    private JmsTemplate jms;
    private Destination destination;

    @Autowired
    public JmsOrderMessageService(JmsTemplate jms, Destination destination) {
        this.jms = jms;
        this.destination = destination;
    }

    /**
     * 使用默认目的地的jms.send()方法
     * 不关心具体的目的地，将异步消息发送至配置中的默认目的地
     * @param order
     */
    @Override
    /*public void sendOrder(Order order) {
        *//**
         * 因为MessageCreator是一个函数式的接口（这里传进去的是一个构造函数）
         * 所以我们可以使用lambda表达式简化匿名内部类
         *//*
        *//*jms.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(order);
            }
        });*//*

        *//*jms.send(session -> {
            return session.createObjectMessage(order);
        });*//*

        // 最简单的lamda表达式写法
        jms.send(session -> session.createObjectMessage(order));
    }*/

    /*public void sendOrder(Order order) {
        jms.send(destination,
                session -> session.createObjectMessage(order));
    }*/

    /**
     * convertAndSend接受一个Destination对象或者String值确认将要发送的目的地
     */
    /*public void sendOrder(Order order) {
        jms.convertAndSend("tacocloud.order.queue", order);
    }*/

    /**
     * 使用方法引用构造一个messagePostProcessor用于给消息添加自定义属性
     */
    public void sendOrder(Order order) {
        jms.convertAndSend("tacocloud.order.queue", order, this::addOrderSource);
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
}
