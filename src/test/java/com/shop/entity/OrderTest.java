package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import com.shop.repository.OrderItemRepository;
import com.shop.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class OrderTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    public Item createItem(){

        Item item = Item.builder()
                .itemNm("테스트 상품")
                .price(10000)
                .itemDetail("상세설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .stockNumber(100)
                .regTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        return item;
    }

    public Order createOrder(){

        Order order = new Order();

        for(int i=0; i<3; i++){
            Item item = this.createItem();
            itemRepository.save(item);
            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .count(10)
                    .orderPrice(1000)
                    .order(order)
                    .build();
            order.getOrderItems().add(orderItem);
        }

        Member member = new Member();
        memberRepository.save(member);

        order.setMember(member);
        orderRepository.save(order);
        return order;
    }

    /* 지연한다는건 proxy 사용한다는것 */
    @Test
    @DisplayName("지연 로딩 테스트")
    @Commit
    public void lazyLoadingTest(){

        Order order = this.createOrder();
        Long orderItemId = order.getOrderItems().get(0).getItem().getId();
        em.flush();
        em.clear();

        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(EntityNotFoundException::new);

        System.out.println("Order class : " + orderItem.getOrder().getClass());
        System.out.println("====================================");
        orderItem.getOrder().getOrderDate();
        System.out.println("====================================");
    }

    @Test
    @DisplayName("고아객체 제거 테스트")
    @Commit
    public void orphanRemovalTest(){
        Order order = this.createOrder();
        order.getOrderItems().remove(0);
        em.flush();
    }

    @Test
    @DisplayName("영속성 전이 테스트")
    @Commit
    public void cascadeTest(){

        Order order = new Order();

        for(int i=0; i<3; i++){
            Item item = this.createItem();
            itemRepository.save(item);
            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .count(10)
                    .orderPrice(1000)
                    .order(order)
                    .build();
            order.getOrderItems().add(orderItem);
        }

        orderRepository.saveAndFlush(order);
        em.clear();

        Order saveOrder = orderRepository.findById(order.getId())
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(3, saveOrder.getOrderItems().size());
    }


}