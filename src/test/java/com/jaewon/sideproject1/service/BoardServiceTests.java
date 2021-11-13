package com.jaewon.sideproject1.service;

import com.jaewon.sideproject1.domain.board.Board;
import com.jaewon.sideproject1.domain.board.BoardRepository;
import com.jaewon.sideproject1.dto.board.BoardRequestDto;
import com.jaewon.sideproject1.dto.board.BoardResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class BoardServiceTests {
    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;

    @AfterEach
    void afterEach() {
        boardRepository.deleteAll();
    }

    @Test
    void createTest() {
        // test
        boardService.create(new BoardRequestDto("제목", "내용", "작성자"));

        assertThat(boardRepository.findAll().get(0).getContent()).isEqualTo("내용");
    }

    @Test
    void updateTest() {
        Board original = boardRepository.save(new Board("제목", "내용", "작성자"));

        // test
        boardService.update(original.getId(), new BoardRequestDto("제목1", "내용1", "작성자1"));

        assertThat(boardRepository.findAll().get(0).getContent()).isEqualTo("내용1");
        assertThat(boardRepository.count()).isEqualTo(1L);
    }

    @Test
    void deleteTest() {
        Board board = boardRepository.save(new Board("제목", "내용", "작성자"));

        // test
        boardService.delete(board.getId());

        assertThat(boardRepository.count()).isEqualTo(0L);
    }

    @Test
    void getBoardsTest() {
        boardRepository.save(new Board("제목1", "내용1", "작성자1"));
        boardRepository.save(new Board("제목2", "내용2", "작성자2"));

        // test
        List<BoardResponseDto> results =  boardService.getBoards();

        assertThat(results.size()).isEqualTo(2);
        assertThat(results.get(0).getContent()).isEqualTo("내용1");
    }

    @Test
    void getBoardTest() {
        Board board1 = boardRepository.save(new Board("제목1", "내용1", "작성자1"));
        Board board2 = boardRepository.save(new Board("제목2", "내용2", "작성자2"));

        // test
        BoardResponseDto result1 = boardService.getBoard(board1.getId(), false);
        BoardResponseDto result2 = boardService.getBoard(board2.getId(), true);

        assertThat(result1.getContent()).isEqualTo("내용1");
        assertThat(result1.getCnt()).isEqualTo(0L);

        assertThat(result2.getContent()).isEqualTo("내용2");
        assertThat(result2.getCnt()).isEqualTo(1L);
    }

    @Test
    void searchByTitleTest() {
        Board board1 = boardRepository.save(new Board("제목1", "내용1", "작성자1"));
        Board board2 = boardRepository.save(new Board("제목2", "내용2", "작성자2"));

        // test
        List<BoardResponseDto> results = boardService.searchByTitle("제목");

        assertThat(results.size()).isEqualTo(2);
        assertThat(results.get(0).getContent()).isEqualTo("내용1");
    }
}
