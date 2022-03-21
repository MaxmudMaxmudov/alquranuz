package com.company.dto.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachDTO {

    private Integer id;
    private String key;
    private String originName;
    private Long size;
    private String filePath;
    private String Extension;
    private LocalDateTime createdDate;
}
