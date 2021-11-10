package com.jaewon.sideproject1.controller.api;

import com.jaewon.sideproject1.dto.BoardRequestDto;
import com.jaewon.sideproject1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardApi {
    private final BoardService boardService;

    @PostMapping("/api/board")
    public void createBoard(@RequestBody BoardRequestDto requestDto) {
        boardService.create(requestDto);
    }

    @PutMapping("/api/board/{id}")
    public void updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        boardService.update(id, requestDto);
    }

    @DeleteMapping("/api/board/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.delete(id);
    }
}
