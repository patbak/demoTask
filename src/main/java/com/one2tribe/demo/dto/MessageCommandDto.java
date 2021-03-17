package com.one2tribe.demo.dto;

import javax.validation.constraints.NotBlank;

public class MessageCommandDto {

    @NotBlank(message = "Cannot be blank")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
