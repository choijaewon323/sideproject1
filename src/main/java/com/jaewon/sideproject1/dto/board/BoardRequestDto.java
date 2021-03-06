package com.jaewon.sideproject1.dto.board;

import com.jaewon.sideproject1.domain.board.Board;
import com.jaewon.sideproject1.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDto {
    private String title;
    private String content;
    private String writer;

    public BoardRequestDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Board toEntity(User user) {
        return new Board(this.title, this.content, this.writer, user);
    }
}
