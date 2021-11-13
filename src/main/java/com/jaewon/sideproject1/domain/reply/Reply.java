package com.jaewon.sideproject1.domain.reply;

import com.jaewon.sideproject1.domain.common.TimeEntity;
import com.jaewon.sideproject1.domain.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Reply extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String writer;

    @ManyToOne
    private Board board;

    public Reply(String content, String writer, Board board) {
        this.content = content;
        this.writer = writer;
        this.board = board;
    }

    public void update(String content, String writer) {
        this.content = content;
        this.writer = writer;
    }
}
