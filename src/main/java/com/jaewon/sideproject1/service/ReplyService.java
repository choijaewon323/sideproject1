package com.jaewon.sideproject1.service;

import com.jaewon.sideproject1.domain.Board;
import com.jaewon.sideproject1.domain.BoardRepository;
import com.jaewon.sideproject1.domain.Reply;
import com.jaewon.sideproject1.domain.ReplyRepository;
import com.jaewon.sideproject1.dto.ReplyRequestDto;
import com.jaewon.sideproject1.dto.ReplyResponseDto;
import com.jaewon.sideproject1.dto.ReplyUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    public List<ReplyResponseDto> getReplies(Long boardId) {
        List<ReplyResponseDto> results = new ArrayList<>();
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException());

        for (Reply reply : replyRepository.findAllByBoard(board)) {
            results.add(new ReplyResponseDto(reply));
        }

        return results;
    }

    public ReplyResponseDto getReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new IllegalArgumentException());

        return new ReplyResponseDto(reply);
    }

    @Transactional
    public ReplyResponseDto create(Long boardId, ReplyRequestDto requestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException());

        return new ReplyResponseDto(replyRepository.save(requestDto.toEntity(board)));
    }

    @Transactional
    public void update(Long replyId, ReplyUpdateRequestDto requestDto) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new IllegalArgumentException());

        reply.update(requestDto.getContent(), reply.getWriter());
    }

    @Transactional
    public void delete(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}
