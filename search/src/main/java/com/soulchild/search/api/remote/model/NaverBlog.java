package com.soulchild.search.api.remote.model;

import lombok.Data;

import java.util.List;

@Data
public class NaverBlog {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<Item> items;
}
