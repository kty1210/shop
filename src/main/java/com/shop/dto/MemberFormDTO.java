package com.shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberFormDTO {

    @NotBlank(message = "이름은 필수입니다")
    private String name;

    @NotEmpty(message = "이메일은 필수입니다")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수입니다")
    @Length(min=4, max=16, message = "길이는 4자리이상 16자리이하입니다")
    private String password;

    @NotEmpty(message = "주소는 필수 입력입니다")
    private String address;
}
