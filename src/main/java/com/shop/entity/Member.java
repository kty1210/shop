package com.shop.entity;

import com.shop.config.common.entity.BaseEntity;
import com.shop.constant.Role;
import com.shop.dto.MemberFormDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
//@Table(name="member")이름이 같을때는 빼도 됨
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {
    
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @Column(unique = true)
    private String email;

    private String password;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember (MemberFormDTO memberFormDTO, PasswordEncoder passwordEncoder){
        Member member = Member.builder()
                .role(Role.ADMIN)
                .email(memberFormDTO.getEmail())
                .address(memberFormDTO.getAddress())
                .name(memberFormDTO.getName())
                .password(passwordEncoder.encode(memberFormDTO.getPassword()))
                .build();
        String password = memberFormDTO.getPassword();

        return member;
    }

}
