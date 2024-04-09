package com.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
//카멜 케이스 -> 자동으로 스네이크 케이스로 바뀌어서 테이블 생성
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;
}