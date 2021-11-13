package com.jaewon.sideproject1.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPasswordUpdateRequestDto {
    private String account;
    private String password;
    private String passwordConfirm;

    public UserPasswordUpdateRequestDto(String account, String password, String passwordConfirm) {
        this.account = account;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }
}
