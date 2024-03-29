package com.shop.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String itemNm;     //상품명 , sql은 대소문자를 구분 안해서 스네이크표기법으로 바뀜
}
