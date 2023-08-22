package mkcrud.mkboard.service;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserCreateForm {

    @Size(min = 2, max = 30)
    @NotEmpty(message = "유저네임을 2~30자까지 입력하세요.")
    private String username;

    @NotEmpty(message = "비밀번호 입력 오류")
    private String password1;

    @NotEmpty(message = "비밀번호 입력 오류")
    private String password2;

    @NotEmpty(message = "이메일 입력 오류")
    @Email
    private String email;
}
