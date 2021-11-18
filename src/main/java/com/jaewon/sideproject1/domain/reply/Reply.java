package com.jaewon.sideproject1.domain.reply;

import com.jaewon.sideproject1.domain.common.TimeEntity;
import com.jaewon.sideproject1.domain.board.Board;
import com.jaewon.sideproject1.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Reply extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPLY_ID")
    private Long id;

    private String content;
    private String writer;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Reply(String content, String writer, Board board) {
        this.content = content;
        this.writer = writer;
        this.board = board;
    }

    public Reply(String content, String writer, Board board, User user) {
        this.content = content;
        this.writer = writer;
        this.board = board;
        this.user = user;
    }

    public void update(String content, String writer) {
        this.content = content;
        this.writer = writer;
    }
}
