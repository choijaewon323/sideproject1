package com.jaewon.sideproject1.dto.user;

import com.jaewon.sideproject1.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
    private String account;
    private String password;

    public UserRequestDto(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public User toEntity() {
        return new User(account, password);
    }
}
