package com.jaewon.sideproject1.service;

import com.jaewon.sideproject1.domain.board.Board;
import com.jaewon.sideproject1.domain.board.BoardRepository;
import com.jaewon.sideproject1.domain.reply.Reply;
import com.jaewon.sideproject1.domain.reply.ReplyRepository;
import com.jaewon.sideproject1.dto.reply.ReplyRequestDto;
import com.jaewon.sideproject1.dto.reply.ReplyResponseDto;
import com.jaewon.sideproject1.dto.reply.ReplyUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ReplyServiceTests {
    @Autowired
    ReplyService replyService;
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    BoardRepository boardRepository;

    Board board;

    @BeforeEach
    void beforeEach() {
        this.board = boardRepository.save(new Board("제목", "내용", "작성자"));
    }

    @AfterEach
    void afterEach() {
        replyRepository.deleteAll();
        boardRepository.deleteAll();
    }

    @Test
    void createTest() {
        // test
        ReplyResponseDto result =
                replyService.create(board.getId(), new ReplyRequestDto("댓글 내용", "댓글 작성자"));

        assertThat(replyRepository.findById(result.getId()).get().getBoard().getId()).isEqualTo(board.getId());
        assertThat(replyRepository.findById(result.getId()).get().getContent()).isEqualTo("댓글 내용");
    }

    @Test
    void updateTest() {
        Reply reply = replyRepository.save(new Reply("댓글 내용", "댓글 작성자", board));

        // test
        replyService.update(reply.getId(), new ReplyUpdateRequestDto("댓글 내용1"));

        assertThat(replyRepository.findById(reply.getId()).get().getContent()).isEqualTo("댓글 내용1");
    }

    @Test
    void deleteTest() {
        Reply reply = replyRepository.save(new Reply("댓글 내용", "댓글 작성자", board));

        // test
        replyService.delete(reply.getId());

        assertThat(replyRepository.count()).isEqualTo(0L);
    }

    @Test
    void getReplyTest() {
        Reply reply1 = replyRepository.save(new Reply("댓글 내용1", "댓글 작성자1", board));
        Reply reply2 = replyRepository.save(new Reply("댓글 내용2", "댓글 작성자2", board));

        // test
        assertThat(replyService.getReply(reply1.getId()).getContent()).isEqualTo("댓글 내용1");
    }

    @Test
    void getRepliesTest() {
        Board newBoard = boardRepository.save(new Board("제목1", "내용1", "작성자1"));
        Reply reply1 = replyRepository.save(new Reply("댓글 내용1", "댓글 작성자1", board));
        Reply reply2 = replyRepository.save(new Reply("댓글 내용2", "댓글 작성자2", board));
        Reply reply3 = replyRepository.save(new Reply("댓글 내용2", "댓글 작성자2", newBoard));

        // test
        assertThat(replyService.getReplies(board.getId()).size()).isEqualTo(2);
    }
}
