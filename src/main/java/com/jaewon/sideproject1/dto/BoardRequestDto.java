package com.jaewon.sideproject1.dto;

import com.jaewon.sideproject1.domain.Board;
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

    public Board toEntity() {
        return new Board(this.title, this.content, this.writer);
    }
}
