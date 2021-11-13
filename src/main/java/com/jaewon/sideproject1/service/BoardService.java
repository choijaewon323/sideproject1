package com.jaewon.sideproject1.service;

import com.jaewon.sideproject1.domain.board.Board;
import com.jaewon.sideproject1.domain.board.BoardRepository;
import com.jaewon.sideproject1.domain.reply.Reply;
import com.jaewon.sideproject1.domain.reply.ReplyRepository;
import com.jaewon.sideproject1.dto.board.BoardRequestDto;
import com.jaewon.sideproject1.dto.board.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    public List<BoardResponseDto> getBoards() {
        List<BoardResponseDto> results = new ArrayList<>();

        for (Board board : boardRepository.findAll()) {
            results.add(new BoardResponseDto(board));
        }

        return results;
    }

    @Transactional
    public BoardResponseDto getBoard(Long id, boolean isCntIncrease) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        if (isCntIncrease) {
            board.addCnt();
        }

        return new BoardResponseDto(board);
    }

    @Transactional
    public void create(BoardRequestDto requestDto) {
        boardRepository.save(requestDto.toEntity());
    }

    @Transactional
    public void update(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        board.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getWriter());
    }

    @Transactional
    public void delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        for (Reply reply : replyRepository.findAllByBoard(board)) {
            replyRepository.deleteById(reply.getId());
        }

        boardRepository.deleteById(id);
    }
}
