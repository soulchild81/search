package com.soulchild.search.api.model;

import com.soulchild.search.api.entity.PopularKeyword;
import lombok.Data;

import java.util.Date;

@Data
public class PopularKeywordDto {
    private int id;
    private String popular_keyword;
    private int count;
    private Date update_time;

    public PopularKeywordDto(PopularKeyword popular){
        popular_keyword = popular.getKeyword();
        count = popular.getCount();
        update_time = popular.getUpdDt();
        id = popular.getKeywordId();
    }
}
