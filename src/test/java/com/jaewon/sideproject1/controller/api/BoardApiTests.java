package com.jaewon.sideproject1.controller.api;

import com.jaewon.sideproject1.domain.board.Board;
import com.jaewon.sideproject1.domain.board.BoardRepository;
import com.jaewon.sideproject1.dto.board.BoardRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardApiTests {
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    BoardRepository boardRepository;

    @AfterEach
    void deleteAll() {
        boardRepository.deleteAll();
    }

    @Test
    void createBoardTest() throws Exception {
        String url = "http://localhost:" + port + "/api/board";
        BoardRequestDto request = new BoardRequestDto("제목", "내용", "작성자");

        ResponseEntity<Long> responseEntity =
                this.testRestTemplate.postForEntity(url, request, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(boardRepository.findAll().get(0).getContent()).isEqualTo("내용");
    }

    @Test
    void updateBoardTest() {
        Board board = boardRepository.save(new Board("제목", "내용", "작성자"));
        String url = "http://localhost:" + port + "/api/board/" + board.getId();
        BoardRequestDto request = new BoardRequestDto("제목1", "내용1", "작성자1");
        HttpEntity<BoardRequestDto> requestEntity = new HttpEntity<>(request);

        ResponseEntity<Long> responseEntity =
                testRestTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(boardRepository.findAll().get(0).getContent()).isEqualTo("내용1");
    }

    @Test
    void deleteBoardTest() {
        Board board = boardRepository.save(new Board("제목", "내용", "작성자"));
        String url = "http://localhost:" + port + "/api/board/" + board.getId();

        ResponseEntity<Long> responseEntity =
                testRestTemplate.exchange(url, HttpMethod.DELETE, null, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(boardRepository.count()).isEqualTo(0L);
    }
}
