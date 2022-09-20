package com.soulchild.search.api.model;

import lombok.Data;

@Data
public class BlogDto {
    private String blog_contents;
    private String blog_title;
    private String blog_url;
    private String post_date;

    public BlogDto(String contents , String title , String url , String datetime){
        blog_contents = contents;
        blog_title = title;
        blog_url = url;
        post_date = datetime;
    }
}
