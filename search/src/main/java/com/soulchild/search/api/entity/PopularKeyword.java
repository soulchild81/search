package com.soulchild.search.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;


@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopularKeyword {
    @Id
    @GeneratedValue
    private int keywordId;

    private String keyword;
    private int count;

    @Column(nullable = false)
    private Date regDate;

    private Date updDt;

}
