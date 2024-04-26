package com.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
//카멜 케이스 -> 자동으로 스네이크 케이스로 바뀌어서 테이블 생성
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    //상품 엔티티
    public static CartItem createCartItem(Cart cart, Item item, int count){
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(count);
        return cartItem;
    }

    //수량 증가 메소드
    public void addCount(int count){
        this.count += count;
    }
}
