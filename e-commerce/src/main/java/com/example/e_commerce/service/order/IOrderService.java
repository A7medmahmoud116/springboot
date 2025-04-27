package com.example.e_commerce.service.order;

import com.example.e_commerce.dto.OrderDto;
import com.example.e_commerce.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);

    OrderDto convertToDto(Order order);
}
