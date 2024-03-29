package com.shop.entity;

import com.shop.entity.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity

//테이블명 설정해주는 annotation
//default 값 = class 명
@Table(name="item")
@Getter
@ToString
@Setter
public class Item {

    @Id
    @Column(name="item_id") // 데이터베이스 열과 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment
    private Long id;            //상품코드

    @Column(nullable = false, length = 50)
    private String itemNm;      //상품명 , sql은 대소문자를 구분 안해서 스네이크표기법으로 바뀜

    @Column(nullable = false)
    private int price;          //가격

    @Column(nullable = false)
    private int stockNumber;    //재고수량

    @Lob // 다량의 데이터 저장시 사용
    @Column(nullable = false)
    private String itemDetail;     // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;     // 상품 판매 상태
    
    private LocalDateTime regTime; // 등록시간
    
    private LocalDateTime updateTime; // 수정시간


}
