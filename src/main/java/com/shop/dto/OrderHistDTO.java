package com.shop.dto;


import com.shop.constant.OrderStatus;
import com.shop.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//주문 정보 담을 DTO
@Getter
@Setter
public class OrderHistDTO {

    public OrderHistDTO(Order order){
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }

    private Long orderId;//주문 아이디

    private String orderDate;//주문 날짜

    private OrderStatus orderStatus;//주문 상태

    private List<OrderItemDTO> orderItemDTOList = new ArrayList<>(); //주문 상품 리스트

    public void addOrderItemDTO(OrderItemDTO orderItemDTO){
        orderItemDTOList.add(orderItemDTO);
    }
}
