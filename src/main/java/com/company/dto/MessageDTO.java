package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO {

    private Integer id;
    private String subject;
    private String email;
    private String text;
    private String content;

    private LocalDateTime usedDate;

    private Boolean used = false;
    private LocalDateTime date = LocalDateTime.now();

    public MessageDTO() {
        this.email = email;
        this.subject = subject;
        this.text = text;
    }
}
