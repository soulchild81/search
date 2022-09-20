package com.soulchild.search.api.remote.model;

import lombok.Data;

import java.util.List;

@Data
public class KakaoBlog {
    private Meta meta;
    private List<Documents> documents;

}
