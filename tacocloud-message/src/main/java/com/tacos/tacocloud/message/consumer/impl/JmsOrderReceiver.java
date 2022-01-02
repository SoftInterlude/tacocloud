package com.tacos.tacocloud.message.consumer.impl;

import com.tacos.tacocloud.domain.Order;
import com.tacos.tacocloud.message.consumer.OrderReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class JmsOrderReceiver implements OrderReceiver {

    private JmsTemplate jms;
    private MessageConverter messageConverter;

    @Autowired
    public JmsOrderReceiver(JmsTemplate jms, MessageConverter messageConverter) {
        this.jms = jms;
        this.messageConverter = messageConverter;
    }

    /**
     * 将载荷转换成领域对象是一个需要两步的步骤，需要将消息转换器注入到组件中
     * @return 领域对象
     */
    /*@Override
    public Order receive() {
        Message message = jms.receive("tacocloud.order.queue");
        try {
            return (Order)messageConverter.fromMessage(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /**
     * 如果只关心领域对象，可以使用receiveAndConvert
     * @return
     */
    public Order receiveMessage() {
        return (Order) jms.receiveAndConvert("tacocloud.order.queue");
    }
}
