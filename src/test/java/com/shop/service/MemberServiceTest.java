package com.shop.service;

import com.shop.dto.MemberFormDTO;
import com.shop.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    //테스트에서는 생성자 주입이 안됨
    @Autowired
    MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember() {
        MemberFormDTO dto = MemberFormDTO.builder()
                .email("test@test.com")
                .name("홍길동")
                .address("기흥구")
                .password("1234")
                .build();
        Member member = Member.createMember(dto, passwordEncoder);
        return member;
    }

    @Test
    @DisplayName("회원가입테스트")
    @Commit
    void saveMemberTest(){
        Member member = createMember();
        System.out.println(member);
        Member member1 =memberService.saveMember(member);
        System.out.println(member);
    }
}