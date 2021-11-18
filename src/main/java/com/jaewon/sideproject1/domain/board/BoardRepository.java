package com.jaewon.sideproject1.domain.board;

import com.jaewon.sideproject1.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitleContains(String title);

    List<Board> findByUser(User user);
}
