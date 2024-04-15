package com.shop.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.shop.dto.QMainItemDTO is a Querydsl Projection type for MainItemDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMainItemDTO extends ConstructorExpression<MainItemDTO> {

    private static final long serialVersionUID = -1091103879L;

    public QMainItemDTO(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> itemNm, com.querydsl.core.types.Expression<String> itemDetail, com.querydsl.core.types.Expression<String> imgUrl, com.querydsl.core.types.Expression<Integer> price) {
        super(MainItemDTO.class, new Class<?>[]{long.class, String.class, String.class, String.class, int.class}, id, itemNm, itemDetail, imgUrl, price);
    }

}

