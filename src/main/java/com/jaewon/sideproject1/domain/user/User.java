package com.jaewon.sideproject1.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    private String account;
    private String password;

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
