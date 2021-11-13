package com.jaewon.sideproject1.domain.user;

public enum UserConfirm {
    USER_CORRECT(0),
    ACCOUNT_WRONG(1),
    PASSWORD_WRONG(2);

    private final int num;

    UserConfirm(int num) {
        this.num = num;
    }
}
