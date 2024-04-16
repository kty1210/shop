package com.shop.service;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.OrderDTO;
import com.shop.entity.Item;
import com.shop.entity.Member;
import com.shop.entity.Order;
import com.shop.entity.OrderItem;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import com.shop.repository.OrderRepository;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    public Item saveItem(){
        Item item = Item.builder()
                .itemNm("테스트 상품")
                .price(10000)
                .itemDetail("테스트 상품 상세 설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .stockNumber(100)
                .build();
        return itemRepository.save(item);
    }

    public Member saveMember(){
        Member member = Member.builder()
                .email("test@test.com")
                .build();
        return memberRepository.save(member);
    }

    @Test
    @DisplayName("주문 테스트")
    public void order(){

        Item item = saveItem();
        Member member = saveMember();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCount(10);
        orderDTO.setItemId(item.getId());

        Long orderId = orderService.order(orderDTO, member.getEmail());

        Order order= orderRepository.findById(orderId).orElseThrow(EntityExistsException::new);

        List<OrderItem> orderItems = order.getOrderItems();

        int totalPrice = orderDTO.getCount()*item.getPrice();

        assertEquals(totalPrice, order.getTotalPrice());
    }
}