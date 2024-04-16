package com.shop.dto;

import com.shop.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

//주문 화면 출력용 DTO
@Getter
@Setter
public class OrderItemDTO {

    public OrderItemDTO(OrderItem orderItem, String imgUrl){
        this.itemNm = orderItem.getItem().getItemNm();
        this.count = orderItem.getCount();
        this.orderPrice = orderItem.getOrderPrice();
        this.imgUrl = imgUrl;
    }

    private String itemNm; //상품명

    private int count; //수량

    private int orderPrice; //금액

    private String imgUrl; //이미지 경로
}
