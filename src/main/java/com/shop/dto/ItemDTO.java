package com.shop.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Long id;

    private String itemNm;

    private Integer price;

    private String itemDetail;

    private String sellStatCd;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}
