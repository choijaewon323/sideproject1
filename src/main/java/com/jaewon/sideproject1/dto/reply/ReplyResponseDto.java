package com.jaewon.sideproject1.dto.reply;

import com.jaewon.sideproject1.domain.board.Board;
import com.jaewon.sideproject1.domain.reply.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyResponseDto {
    private Long id;
    private String content;
    private String writer;
    private String createdDate;
    private Board board;

    public ReplyResponseDto(Long id, String content, String writer, String createdDate, Board board) {
        this.id = id;
        this.content = content;
        this.writer = writer;
        this.createdDate = createdDate;
        this.board = board;
    }

    public ReplyResponseDto(Reply reply) {
        this.id = reply.getId();
        this.content = reply.getContent();
        this.writer = reply.getWriter();
        this.createdDate = reply.getCreatedDate();
        this.board = reply.getBoard();
    }
}
