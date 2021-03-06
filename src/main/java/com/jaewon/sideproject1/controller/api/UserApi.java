package com.jaewon.sideproject1.controller.api;

import com.jaewon.sideproject1.domain.user.UserConfirm;
import com.jaewon.sideproject1.dto.user.UserPasswordUpdateRequestDto;
import com.jaewon.sideproject1.dto.user.UserRequestDto;
import com.jaewon.sideproject1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UserApi {
    private final UserService userService;

    @PostMapping("/api/user")
    public boolean createUser(@RequestBody UserRequestDto requestDto) {
        return userService.create(requestDto);
    }

    @PutMapping("/api/user")
    public UserConfirm updateUserPassword(@RequestBody UserPasswordUpdateRequestDto requestDto) {
        return userService.updatePassword(requestDto);
    }

    @PostMapping("/api/user/auth")
    public UserConfirm login(@RequestBody UserRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("username", requestDto.getAccount());

        return userService.userConfirm(requestDto);
    }

    @PostMapping("/api/user/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();
    }
}
