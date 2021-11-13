package com.jaewon.sideproject1.controller;

import com.jaewon.sideproject1.service.BoardService;
import com.jaewon.sideproject1.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final BoardService boardService;
    private final ReplyService replyService;

    @GetMapping("/")
    public String getMain(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "redirect:/login";
        }

        model.addAttribute("boards", boardService.getBoards());
        model.addAttribute("username", (String)session.getAttribute("username"));

        return "/board/main";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam String searchKeyword) {
        model.addAttribute("boards", boardService.searchByTitle(searchKeyword));

        return "/board/main";
    }

    @GetMapping("/board/{id}")
    public String getBoard(@PathVariable Long id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "redirect:/login";
        }

        model.addAttribute("board", boardService.getBoard(id, true));
        model.addAttribute("replies", replyService.getReplies(id));
        model.addAttribute("username", (String)session.getAttribute("username"));

        return "/board/detail";
    }

    @GetMapping("/board/new")
    public String getNew(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", (String)session.getAttribute("username"));

        return "/board/new";
    }

    @GetMapping("/board/update/{id}")
    public String getUpdate(@PathVariable Long id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "redirect:/login";
        }

        model.addAttribute("board", boardService.getBoard(id, false));

        return "/board/update";
    }
}
