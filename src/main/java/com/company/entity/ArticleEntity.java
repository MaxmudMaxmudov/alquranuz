package com.company.entity;

import com.company.enums.ArticleStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "article")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleEntity extends BaseEntity {

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private ProfileEntity publisher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;

    @Column(name = "published_date")
    private LocalDateTime publisherDate;

    @Enumerated(EnumType.STRING)
    private ArticleStatus status;


}
