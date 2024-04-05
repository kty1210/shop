package com.shop.controller;

import com.shop.dto.ItemDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafExController {
    @GetMapping("/ex01")
    public String thymeleafExample01(Model model){
        model.addAttribute("data","타임리프 예제입니다123");
        return "thymeleafEx/thymeleafEx01";
    }

    @GetMapping("/ex02")
    public String thymeleafExample02(Model model) {
        ItemDTO itemDTO = ItemDTO.builder()
                .itemNm("테스트 상품1")
                .price(10000)
                .itemDetail("테스트 상품 상세 설명")
                .regTime(LocalDateTime.now())
                .build();

        model.addAttribute("itemDTO", itemDTO);

        return "thymeleafEx/thymeleafEx02";
    }

    @GetMapping("/ex03")
    public String thymeleafExample03(Model model) {

        List<ItemDTO> itemDTOList = new ArrayList<>();

        for(int i = 1; i<=10; i++){
            ItemDTO itemDTO = new ItemDTO(); //생성자 dto에 만들어야됨

            itemDTO.setItemDetail("상품 상세 설명" + i);
            itemDTO.setItemNm("테스트 상품" + i);
            itemDTO.setPrice(1000*i);
            itemDTO.setRegTime(LocalDateTime.now());

            itemDTOList.add(itemDTO);

        }

        model.addAttribute("itemDTOList", itemDTOList);

        return "thymeleafEx/thymeleafEx03";
    }

    @GetMapping("/ex04")
    public String thymeleafExample04(Model model) {

        List<ItemDTO> itemDTOList = new ArrayList<>();

        for(int i = 1; i<=10; i++){
            ItemDTO itemDTO = new ItemDTO();

            itemDTO.setItemDetail("상품 상세 설명" + i);
            itemDTO.setItemNm("테스트 상품" + i);
            itemDTO.setPrice(1000*i);
            itemDTO.setRegTime(LocalDateTime.now());

            itemDTOList.add(itemDTO);

        }

        model.addAttribute("itemDTOList", itemDTOList);

        return "thymeleafEx/thymeleafEx04";
    }

    @GetMapping("/ex05")
    public String thymeleafExample05(Model model) {

        return "thymeleafEx/thymeleafEx05";
    }

    @GetMapping("/ex06")
    public String thymeleafExample06(@RequestParam("param1") String param1, @RequestParam("param2") String param2, Model model) {

        model.addAttribute("param1", param1);
        model.addAttribute("param2", param2);
        return "thymeleafEx/thymeleafEx06";
    }

    @GetMapping("/ex07")
    public String thymeleafExample07(){
        return "thymeleafEx/thymeleafEx07";
    }
}
