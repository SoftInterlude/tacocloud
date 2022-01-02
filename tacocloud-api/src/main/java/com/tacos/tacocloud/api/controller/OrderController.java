package com.tacos.tacocloud.api.controller;

import com.tacos.tacocloud.data.repository.OrderRepository;
import com.tacos.tacocloud.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "orders", produces = "application/json")
public class OrderController {

  private OrderRepository orderRepository;

  @Autowired
  public OrderController(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  /*Put从语义上说提交的应该是完整的数据，如果没提交则视为null*/
  @PutMapping("/{orderId}")
  public Order putOrder(@RequestBody Order order) {
    return orderRepository.save(order);
  }

  /*Patch的本质是局部更新，通过判断变更字段实现，更适用于大数据量的更新*/
  @PatchMapping(path = "/{orderId}", consumes = "application/json")
  public Order patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order patch) {
    Order order = orderRepository.findById(orderId).get();
    if (patch.getDeliveryName() != null) {
      order.setDeliveryName(patch.getDeliveryName());
    }
    if (patch.getDeliveryStreet() != null) {
      order.setDeliveryStreet(patch.getDeliveryStreet());
    }
    if (patch.getDeliveryCity() != null) {
      order.setDeliveryCity(patch.getDeliveryCity());
    }
    if (patch.getDeliveryState() != null) {
      order.setDeliveryState(patch.getDeliveryState());
    }
    if (patch.getDeliveryZip() != null) {
      order.setDeliveryZip(patch.getDeliveryZip());
    }
    if (patch.getDeliveryName() != null) {
      order.setDeliveryName(patch.getDeliveryName());
    }
    if (patch.getCcNumber() != null) {
      order.setCcNumber(patch.getCcNumber());
    }
    if (patch.getCcExpiration() != null) {
      order.setCcExpiration(patch.getCcExpiration());
    }
    if (patch.getCcCVV() != null) {
      order.setCcCVV(patch.getCcCVV());
    }
    return orderRepository.save(order);
  }

  /*Delete请求有多种处理方法，方法一可以如下在捕捉删除不存在内容后没有任何处理， 出发点是对于删除的资源是否存在并不重要*/
  @DeleteMapping("/{orderId}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteOrder(@PathVariable("orderId") Long orderId) {
    try {
      orderRepository.deleteById(orderId);
    } catch (EmptyResultDataAccessException e) {}
  }

  /*或者我们可以通过方法二返回NOT_FOUND的ResponseEntity来实现*/
  /*@DeleteMapping("/{orderId}")
  public ResponseEntity deleteOrder(@PathVariable("orderId") Long orderId) {
    try {
      orderRepository.deleteById(orderId);
      return new ResponseEntity(orderId, HttpStatus.NO_CONTENT);
    } catch (EmptyResultDataAccessException e) {
      return new ResponseEntity(ResponseEntity.noContent(), HttpStatus.NOT_FOUND);
    }
  }*/

}
