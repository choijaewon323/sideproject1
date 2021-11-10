package com.jaewon.sideproject1.controller;

import com.jaewon.sideproject1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final BoardService boardService;

    @GetMapping("/")
    public String getMain(Model model) {
        model.addAttribute("boards", boardService.getBoards());

        return "main";
    }

    @GetMapping("/board/{id}")
    public String getBoard(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoard(id, true));

        return "detail";
    }

    @GetMapping("/board/new")
    public String getNew() {
        return "new";
    }

    @GetMapping("/board/update/{id}")
    public String getUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoard(id, false));

        return "update";
    }
}
