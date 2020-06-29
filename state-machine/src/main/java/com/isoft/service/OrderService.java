package com.isoft.service;

import com.isoft.entity.Order;

import java.util.Map;

public interface OrderService {

    Order create();

    Order pay(int id);

    Order deliver(int id);

    Order receive(int id);

    Map<Integer, Order> getOrders();
}
