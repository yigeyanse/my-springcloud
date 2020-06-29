package com.isoft.entity;

import com.isoft.enums.OrderStatus;
import lombok.Data;

@Data
public class Order {

    private int id;
    private OrderStatus Status;
}
