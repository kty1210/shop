package com.shop.controller;

import com.shop.dto.MemberFormDTO;
import com.shop.entity.Member;
import com.shop.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDTO", new MemberFormDTO());
        return "members/memberForm";
    }


    @PostMapping("/new")
    public String memberForm(@Valid MemberFormDTO memberFormDTO,
                             BindingResult bindingResult, Model model){

            if(bindingResult.hasErrors()){
                return "members/memberForm";
            } try {
                Member member = Member.createMember(memberFormDTO, passwordEncoder);
                memberService.saveMember(member);
            } catch (IllegalStateException e){
                model.addAttribute("errorMessage", e.getMessage());
                return "members/memberForm";
            }
        return "redirect:/";
        }



}
