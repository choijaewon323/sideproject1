package com.jaewon.sideproject1.dto.reply;

import com.jaewon.sideproject1.domain.board.Board;
import com.jaewon.sideproject1.domain.reply.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ReplyRequestDto {
    private String content;
    private String writer;

    public ReplyRequestDto(String content, String writer) {
        this.content = content;
        this.writer = writer;
    }

    public Reply toEntity(Board board) {
        return new Reply(content, writer, board);
    }
}
