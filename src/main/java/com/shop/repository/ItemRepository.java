package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*JPA 사용하게 되면 
1. CRUD 2. Paging 3. Sort 
SQL문 사용하지 않고 실행됨*/

                                        /*JPA에서는 <entity, primarykey (속성)>*/
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemNm(String itemNm);
    List<Item> findByItemNmOrItemDetail (String itemNm, String itemDetail);
    List<Item> findByPriceLessThan(Integer price);
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
}
