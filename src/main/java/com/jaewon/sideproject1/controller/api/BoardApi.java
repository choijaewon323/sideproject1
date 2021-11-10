package com.jaewon.sideproject1.controller.api;

import com.jaewon.sideproject1.domain.Board;
import com.jaewon.sideproject1.domain.BoardRepository;
import com.jaewon.sideproject1.dto.BoardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardApi {
    private final BoardRepository boardRepository;

    @Transactional
    @PostMapping("/api/board")
    public void createBoard(@RequestBody BoardRequestDto requestDto) {
        boardRepository.save(requestDto.toEntity());
    }

    @Transactional
    @PutMapping("/api/board/{id}")
    public void updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        board.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getWriter());
    }

    @Transactional
    @DeleteMapping("/api/board/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
    }
}
