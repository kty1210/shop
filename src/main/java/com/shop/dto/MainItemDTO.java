package com.shop.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//다시 컴파일을 통해 Qitem 설정해야함
public class MainItemDTO {

    private Long id;
    private String itemNm;
    private String itemDetail;
    private String imgUrl;
    private Integer price;

    //특정 프로젝션을 반환한다는 것을 명시적으로 나타내는 어노테이션
    @QueryProjection
    public MainItemDTO(Long id, String itemNm, String itemDetail, String imgUrl, Integer price){

        this.id = id;
        this.itemNm = itemNm;
        this.itemDetail = itemDetail;
        this.imgUrl = imgUrl;
        this.price = price;
    }
}
