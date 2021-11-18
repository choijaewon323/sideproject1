package com.jaewon.sideproject1.domain.board;

import com.jaewon.sideproject1.domain.common.TimeEntity;
import com.jaewon.sideproject1.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Board extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    private String title;
    private String content;
    private String writer;
    private Long cnt = 0L;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Board(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Board(String title, String content, String writer, User user) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.user = user;
    }

    public void update(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public void addCnt() {
        this.cnt++;
    }
}
