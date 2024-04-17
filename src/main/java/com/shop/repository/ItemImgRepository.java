package com.shop.repository;

import com.shop.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);

    //대표 이미지 찾는 메소드
    ItemImg findByItemIdAndRepImgYn(Long itemId, String repImgYn);
}
