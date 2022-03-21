package com.company.dto.article;

import com.company.enums.ArticleStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDTO {

    private Integer id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Integer profileId;

    private Integer publisherId;

    private ArticleStatus status;

    private LocalDateTime createdDate;

    private LocalDateTime publisherDate;


}
