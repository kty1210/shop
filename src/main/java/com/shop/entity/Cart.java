package com.shop.entity;

import com.shop.config.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Cart extends BaseEntity {

    @Id
    @Column(name="cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) /* 데이터가 많아지면 지연로딩을 해야 안정적인 ex) 즉시로딩시 1대 1관계에서 한쪽이 로딩이 안되면 오류 */
    @JoinColumn(name="member_id") /*안해도 됨*/
    private Member member;



}
