package com.jaewon.sideproject1.controller;

import com.jaewon.sideproject1.domain.Board;
import com.jaewon.sideproject1.domain.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final BoardRepository boardRepository;

    @GetMapping("/")
    public String getMain(Model model) {
        model.addAttribute("boards", boardRepository.findAll());
        return "main";
    }

    @Transactional
    @GetMapping("/board/{id}")
    public String getBoard(@PathVariable Long id, Model model) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        board.addCnt();
        model.addAttribute("board", board);
        return "detail";
    }

    @GetMapping("/board/new")
    public String getNew() {
        return "new";
    }

    @GetMapping("/board/update/{id}")
    public String getUpdate(@PathVariable Long id, Model model) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        model.addAttribute("board", board);
        return "update";
    }
}
