package com.jaewon.sideproject1.dto.board;

import com.jaewon.sideproject1.domain.board.Board;
import com.jaewon.sideproject1.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private Long cnt;
    private String createdDate;
    private User user;

    public BoardResponseDto(Long id, String title, String content, String writer, Long cnt, String createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.cnt = cnt;
        this.createdDate = createdDate;
    }

    public BoardResponseDto(Long id, String title, String content, String writer, Long cnt, String createdDate, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.cnt = cnt;
        this.createdDate = createdDate;
        this.user = user;
    }

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.cnt = board.getCnt();
        this.createdDate = board.getCreatedDate();
        this.user = board.getUser();
    }
}
