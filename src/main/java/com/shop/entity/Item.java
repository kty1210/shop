package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemFormDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;          //상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm;  // 상품명

    @Column(nullable = false)
    private int price;      // 가격

    @Column(nullable = false)
    private int stockNumber; // 재고수량

    @Lob  // 대용량의 데이터를 저장
    @Column(nullable = false)
    private String itemDetail; // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; // 상품 판매 상태

    private LocalDateTime regTime; // 등록 시간

    private LocalDateTime updateTime; // 수정 시간

    public void updateItem(ItemFormDTO itemFormDTO) {
        this.itemNm = itemFormDTO.getItemNm();
        this.price = itemFormDTO.getPrice();
        this.stockNumber = itemFormDTO.getStockNumber();
        this.itemDetail = itemFormDTO.getItemDetail();
        this.itemSellStatus = itemFormDTO.getItemSellStatus();
    }
}
