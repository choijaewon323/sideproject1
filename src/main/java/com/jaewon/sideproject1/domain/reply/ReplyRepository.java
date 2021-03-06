package com.jaewon.sideproject1.domain.reply;

import com.jaewon.sideproject1.domain.board.Board;
import com.jaewon.sideproject1.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByBoard(Board board);

    List<Reply> findByUser(User user);
}
