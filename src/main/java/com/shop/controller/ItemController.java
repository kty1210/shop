package com.shop.controller;

import com.shop.dto.ItemFormDTO;
import com.shop.service.ItemService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("admin/item/new")
    public String itemForm(Model model){
        model.addAttribute("itemFormDTO", new ItemFormDTO());
        return "/item/itemForm";
    }

    @PostMapping(value = "/admin/item/new")
    public String itemNew(@Valid ItemFormDTO itemFormDTO, BindingResult bindingResult, Model model,
                          @RequestParam("itemImgFile")List<MultipartFile> itemImgFileList){
        if(bindingResult.hasErrors()){
            return "item/itemForm";
        }
        if(itemImgFileList.get(0).isEmpty() && itemFormDTO.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "item/itemForm";
        }
        try{
            itemService.saveItem(itemFormDTO, itemImgFileList);
        } catch (Exception e){
            model.addAttribute("errorMessage", "상품등록중 에러가 발생하였습니다.");
            return "item/itemForm";
        }
        return "redirect:/";
    }

    @GetMapping("/admin/item/{itemId}")
    public String itemDtl(@PathVariable("itemId") Long itemId, Model model){
        try{
            ItemFormDTO itemFormDTO = itemService.getItemDtl(itemId);
            model.addAttribute("itemFormDTO", itemFormDTO);
        } catch(EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지않는 상품 입니다.");
            model.addAttribute("itemFormDTO", new ItemFormDTO());
            return "item/itemForm";
        }

        return "item/itemForm";
    }

}
