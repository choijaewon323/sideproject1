package com.jaewon.sideproject1.service;

import com.jaewon.sideproject1.domain.user.User;
import com.jaewon.sideproject1.domain.user.UserConfirm;
import com.jaewon.sideproject1.domain.user.UserRepository;
import com.jaewon.sideproject1.dto.user.UserPasswordUpdateRequestDto;
import com.jaewon.sideproject1.dto.user.UserRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @AfterEach
    void afterEach() {
        userRepository.deleteAll();
    }

    @Test
    void createTest() {
        userRepository.save(new User("아이디", "비밀번호"));

        // test
        assertThat(userService.create(new UserRequestDto("아이디", "비밀번호"))).isFalse();
        assertThat(userService.create(new UserRequestDto("아이디1", "비밀번호1"))).isTrue();
        assertThat(userRepository.findAll().get(1).getAccount()).isEqualTo("아이디1");
        assertThat(userRepository.count()).isEqualTo(2L);
    }

    @Test
    void userConfirmTest() {
        userRepository.save(new User("아이디", "비밀번호"));

        // test
        assertThat(userService.userConfirm(new UserRequestDto("아이디1", "비밀번호")))
                .isEqualTo(UserConfirm.ACCOUNT_WRONG);
        assertThat(userService.userConfirm(new UserRequestDto("아이디", "비밀번호1")))
                .isEqualTo(UserConfirm.PASSWORD_WRONG);
        assertThat(userService.userConfirm(new UserRequestDto("아이디", "비밀번호")))
                .isEqualTo(UserConfirm.USER_CORRECT);
    }

    @Test
    void updatePasswordTest() {
        User user = userRepository.save(new User("아이디", "비밀번호"));

        // test
        assertThat(userService.updatePassword(
                new UserPasswordUpdateRequestDto("아이디", "비밀번호", "비밀번호1")
        )).isEqualTo(UserConfirm.PASSWORD_CONFIRM_NOT_MATCH);
        assertThat(userService.updatePassword(
                new UserPasswordUpdateRequestDto("아이디", "비밀번호", "비밀번호")
        )).isEqualTo(UserConfirm.PASSWORD_UPDATE_SUCCESS);
    }
}
