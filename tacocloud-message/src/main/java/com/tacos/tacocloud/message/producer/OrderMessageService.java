package com.tacos.tacocloud.message.producer;

import com.tacos.tacocloud.domain.Order;

public interface OrderMessageService {

    void sendOrder(Order order);
}
