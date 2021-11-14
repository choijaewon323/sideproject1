package com.jaewon.sideproject1.domain.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionCommon {
    public static boolean isSessionValid(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        return (session != null);
    }

    public static Object getAttribute(String name, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        return session.getAttribute(name);
    }

    public static String redirectToLogin() {
        return "redirect:/login";
    }
}
