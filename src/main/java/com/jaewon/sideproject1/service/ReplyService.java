package com.jaewon.sideproject1.service;

import com.jaewon.sideproject1.domain.board.Board;
import com.jaewon.sideproject1.domain.board.BoardRepository;
import com.jaewon.sideproject1.domain.reply.Reply;
import com.jaewon.sideproject1.domain.reply.ReplyRepository;
import com.jaewon.sideproject1.domain.user.User;
import com.jaewon.sideproject1.domain.user.UserRepository;
import com.jaewon.sideproject1.dto.reply.ReplyRequestDto;
import com.jaewon.sideproject1.dto.reply.ReplyResponseDto;
import com.jaewon.sideproject1.dto.reply.ReplyUpdateRequestDto;
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
    private final UserRepository userRepository;

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

    public List<ReplyResponseDto> findByUser(String account) {
        User user = userRepository.findByAccount(account).orElseThrow(IllegalArgumentException::new);
        List<ReplyResponseDto> results = new ArrayList<>();

        for (Reply reply :
                replyRepository.findByUser(user)) {
            results.add(new ReplyResponseDto(reply));
        }

        return results;
    }
}
