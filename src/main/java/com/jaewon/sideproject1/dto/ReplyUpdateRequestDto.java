package com.jaewon.sideproject1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyUpdateRequestDto {
    private String content;

    public ReplyUpdateRequestDto(String content) {
        this.content = content;
    }
}
