package com.tacos.tacocloud.message.config;

import com.tacos.tacocloud.domain.Order;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用Configuration和Bean注解配置Spring Boot工程的全局属性
 */
@Configuration
public class JmsOrderMessageConfig {

    @Bean
    public Destination destination() {
        return new ActiveMQQueue("tacocloud.order.queue");
    }

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        // 通过调用消息转换器将合成类型名映射为Order类？
        messageConverter.setTypeIdPropertyName("_typeId");
        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("order", Order.class);
        messageConverter.setTypeIdMappings(typeIdMappings);

        return messageConverter;
    }
}
