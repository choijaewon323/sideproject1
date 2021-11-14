package com.jaewon.sideproject1.controller;

import com.jaewon.sideproject1.domain.common.SessionCommon;
import com.jaewon.sideproject1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/user/{username}")
    public String getUserDetail(@PathVariable String username, Model model, HttpServletRequest request) {
        if (!SessionCommon.isSessionValid(request)) {
            return SessionCommon.redirectToLogin();
        }

        model.addAttribute("username", (String)SessionCommon.getAttribute("username", request));
        return "/user/userDetail";
    }

    @GetMapping("/user/update/{username}")
    public String getUpdatePassword(@PathVariable String username, Model model, HttpServletRequest request) {
        if (!SessionCommon.isSessionValid(request)) {
            return SessionCommon.redirectToLogin();
        }
        model.addAttribute("username", (String)SessionCommon.getAttribute("username", request));
        return "/user/updatePassword";
    }
}
