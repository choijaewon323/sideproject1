package com.jaewon.sideproject1.controller.api;

import com.jaewon.sideproject1.dto.ReplyRequestDto;
import com.jaewon.sideproject1.dto.ReplyResponseDto;
import com.jaewon.sideproject1.dto.ReplyUpdateRequestDto;
import com.jaewon.sideproject1.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReplyApi {
    private final ReplyService replyService;

    @PostMapping("/api/reply/{boardId}")
    public ReplyResponseDto createReply(@PathVariable Long boardId, @RequestBody ReplyRequestDto requestDto) {
        return replyService.create(boardId, requestDto);
    }

    @PutMapping("/api/reply/{replyId}")
    public void updateReply(@PathVariable Long replyId, @RequestBody ReplyUpdateRequestDto requestDto) {
        replyService.update(replyId, requestDto);
    }

    @DeleteMapping("/api/reply/{replyId}")
    public void deleteReply(@PathVariable Long replyId) {
        replyService.delete(replyId);
    }
}
