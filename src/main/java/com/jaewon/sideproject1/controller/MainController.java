package com.jaewon.sideproject1.controller;

import com.jaewon.sideproject1.domain.common.SessionCommon;
import com.jaewon.sideproject1.service.BoardService;
import com.jaewon.sideproject1.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final BoardService boardService;
    private final ReplyService replyService;

    @GetMapping("/")
    public String getMain(Model model, HttpServletRequest request) {
        if (!SessionCommon.isSessionValid(request)) {
            return SessionCommon.redirectToLogin();
        }

        model.addAttribute("boards", boardService.getBoards());
        model.addAttribute("username", (String)SessionCommon.getAttribute("username", request));

        return "/board/main";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam String searchKeyword, HttpServletRequest request) {
        if (!SessionCommon.isSessionValid(request)) {
            return SessionCommon.redirectToLogin();
        }

        model.addAttribute("boards", boardService.searchByTitle(searchKeyword));

        return "/board/main";
    }

    @GetMapping("/board/{id}")
    public String getBoard(@PathVariable Long id, Model model, HttpServletRequest request) {
        if (!SessionCommon.isSessionValid(request)) {
            return SessionCommon.redirectToLogin();
        }

        model.addAttribute("board", boardService.getBoard(id, true));
        model.addAttribute("replies", replyService.getReplies(id));
        model.addAttribute("username", (String)SessionCommon.getAttribute("username", request));

        return "/board/detail";
    }

    @GetMapping("/board/new")
    public String getNew(Model model, HttpServletRequest request) {
        if (!SessionCommon.isSessionValid(request)) {
            return SessionCommon.redirectToLogin();
        }

        model.addAttribute("username", (String)SessionCommon.getAttribute("username", request));

        return "/board/new";
    }

    @GetMapping("/board/update/{id}")
    public String getUpdate(@PathVariable Long id, Model model, HttpServletRequest request) {
        if (!SessionCommon.isSessionValid(request)) {
            return SessionCommon.redirectToLogin();
        }

        model.addAttribute("board", boardService.getBoard(id, false));

        return "/board/update";
    }
}
