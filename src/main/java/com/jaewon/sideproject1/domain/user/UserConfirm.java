package com.jaewon.sideproject1.domain.user;

public enum UserConfirm {
    USER_CORRECT(0),
    ACCOUNT_WRONG(1),
    PASSWORD_WRONG(2),
    PASSWORD_CONFIRM_NOT_MATCH(3),
    PASSWORD_UPDATE_SUCCESS(4);

    private final int num;

    UserConfirm(int num) {
        this.num = num;
    }
}
