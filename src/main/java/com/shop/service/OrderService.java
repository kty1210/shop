package com.shop.service;

import com.shop.dto.OrderDTO;
import com.shop.dto.OrderHistDTO;
import com.shop.dto.OrderItemDTO;
import com.shop.entity.*;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import com.shop.repository.OrderRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemImgRepository itemImgRepository;

    public Long order(OrderDTO orderDTO, String email){

        //주문할 상품 조회
        Item item = itemRepository.findById(orderDTO.getItemId())
                .orElseThrow(EntityExistsException::new);

        //이메일로 회원 정보 조회
        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();

        OrderItem orderItem =
                OrderItem.createOrderItem(item, orderDTO.getCount());

        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }

    //주문 목록 조회
    @Transactional(readOnly = true)
    public Page<OrderHistDTO> getOrderList(String email, Pageable pageable){

        List<Order> orders = orderRepository.findOrders(email, pageable);
        //주문의 총 수
        Long totalCount = orderRepository.countOrder(email);

        List<OrderHistDTO> orderHistDTOs = new ArrayList<>();

        for(Order order : orders){
            OrderHistDTO orderHistDTO = new OrderHistDTO(order);
            List<OrderItem> orderItems = order.getOrderItems();
            for(OrderItem orderItem : orderItems){
                ItemImg itemImg = itemImgRepository.findByItemIdAndRepimgYn(orderItem.getItem().getId(), "Y");
                OrderItemDTO orderItemDTO = new OrderItemDTO(orderItem, itemImg.getImgUrl());
                orderHistDTO.addOrderItemDTO(orderItemDTO);
            }
            orderHistDTOs.add(orderHistDTO);
        }

        return new PageImpl<OrderHistDTO>(orderHistDTOs, pageable, totalCount);

    }
}
