package com.tacos.tacocloud.message.consumer;

import com.tacos.tacocloud.domain.Order;

public interface OrderReceiver {

    Order receiveMessage();
}
