package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "attach")
public class AttachEntity  extends BaseEntity{

    @Column(name = "key")
    private String key;
    @Column(name = "origin_name")
    private String originName;
    @Column(name = "size")
    private Long size;
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "extension")
    private String extension;


}
